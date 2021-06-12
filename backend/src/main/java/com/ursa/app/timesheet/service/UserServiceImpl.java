package com.ursa.app.timesheet.service;

import com.ursa.app.timesheet.model.dto.mapper.UserMapper;
import com.ursa.app.timesheet.model.dto.mapper.UserWorkMapper;
import com.ursa.app.timesheet.model.dto.mapper.WorkMapper;
import com.ursa.app.timesheet.model.dto.UserDto;
import com.ursa.app.timesheet.model.dto.UserWorkDto;
import com.ursa.app.timesheet.model.dto.WorkDto;
import com.ursa.app.timesheet.exception.GlobalException;
import com.ursa.app.timesheet.model.entity.Role;
import com.ursa.app.timesheet.model.entity.User;
import com.ursa.app.timesheet.model.entity.UserRoles;
import com.ursa.app.timesheet.model.entity.Work;
import com.ursa.app.timesheet.repository.RoleRepository;
import com.ursa.app.timesheet.repository.UserRepository;
import com.ursa.app.timesheet.repository.WorkRepository;
import com.ursa.app.timesheet.exception.EntityType;
import com.ursa.app.timesheet.exception.ExceptionType;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ursa.app.timesheet.exception.EntityType.USER;
import static com.ursa.app.timesheet.exception.ExceptionType.DUPLICATE_ENTITY;
import static com.ursa.app.timesheet.exception.ExceptionType.ENTITY_NOT_FOUND;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private WorkRepository workRepository;

    @Override
    public UserDto signup(UserDto userDto) {
        Role userRole;
        User user = userRepository.findByUsername(userDto.getUsername());
        if (user != null) {
        	throw exception(USER, DUPLICATE_ENTITY, userDto.getUsername());        	
        }
        user = userRepository.findByEmail(userDto.getEmail());
        if (user != null) {
        	throw exception(USER, DUPLICATE_ENTITY, userDto.getEmail());        	
        }

        if (userDto.isAdmin()) {
            userRole = roleRepository.findByRole(UserRoles.ADMIN);
        } else {
            userRole = roleRepository.findByRole(UserRoles.EMPLOYEE);
        }
        user = new User()
                .setEmail(userDto.getEmail())
                .setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()))
                .setRoles(new HashSet<>(Arrays.asList(userRole)))
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setMobileNumber(userDto.getMobileNumber());
        return UserMapper.convert(userRepository.save(user));
    }
    
    /**
     * Search an existing user
     *
     * @param username
     * @return
     */
    @Transactional
    public UserDto findUserByUsername(String username) {
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        }
        return null;
    }

    /**
     * Search an existing user
     *
     * @param email
     * @return
     */
    @Transactional
    public UserDto findUserByEmail(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            return modelMapper.map(user.get(), UserDto.class);
        }
        return null;
    }

    /**
     * Update User Profile
     *
     * @param userDto
     * @return
     */
    @Override
    public UserDto updateProfile(UserDto userDto) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
        if (user.isPresent()) {
            User userModel = user.get();
            userModel.setFirstName(userDto.getFirstName())
                    .setLastName(userDto.getLastName())
                    .setMobileNumber(userDto.getMobileNumber());
            return UserMapper.convert(userRepository.save(userModel));
        }
        throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
    }

    /**
     * Change Password
     *
     * @param userDto
     * @param newPassword
     * @return
     */
    @Override
    public UserDto changePassword(UserDto userDto, String newPassword) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
        if (user.isPresent()) {
            User userModel = user.get();
            userModel.setPassword(bCryptPasswordEncoder.encode(newPassword));
            return UserMapper.convert(userRepository.save(userModel));
        }
        throw exception(USER, ENTITY_NOT_FOUND, userDto.getEmail());
    }       

    @Override
	public Page<UserDto> search(String search, Pageable pageable) {
		Page<User> users = userRepository.search(search, pageable);
		return users.map(UserMapper::convert);
	}

	/**
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return GlobalException.throwException(entityType, exceptionType, args);
    }

	@Override
	public List<WorkDto> getWorks(Long id) {
		User user = userRepository.findById(id).get();
		if(user == null ) throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, id.toString());
		List<Work> works = new ArrayList<Work>(user.getWorks());
		return works.stream().map(WorkMapper::convert).collect(Collectors.toList());
	}

	@Override
	public UserWorkDto addWork(Long id, Long workId) {
		User user = userRepository.findById(id).get();
		if(user == null ) throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, id.toString());
		Work work = workRepository.findById(workId).get();
		if(work == null ) throw exception(EntityType.WORK, ExceptionType.ENTITY_NOT_FOUND, workId.toString());
		user.getWorks().add(work);
		userRepository.save(user);
		return UserWorkMapper.convert(user);
	}

	@Override
	public UserWorkDto deleteWork(Long userId, Long workId) {
		User user = userRepository.findById(userId).get();
		if(user == null ) throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, userId.toString());
		Work work = workRepository.findById(workId).get();
		if(work == null ) throw exception(EntityType.WORK, ExceptionType.ENTITY_NOT_FOUND, workId.toString());
		user.getWorks().remove(work);
		userRepository.save(user);
		return UserWorkMapper.convert(user);
	}
}
