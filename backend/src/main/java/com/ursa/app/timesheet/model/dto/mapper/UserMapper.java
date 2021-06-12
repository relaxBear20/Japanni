package com.ursa.app.timesheet.model.dto.mapper;

import java.util.HashSet;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ursa.app.timesheet.model.dto.RoleDto;
import com.ursa.app.timesheet.model.dto.UserDto;
import com.ursa.app.timesheet.model.dto.WorkDto;
import com.ursa.app.timesheet.model.entity.User;

@Component
public class UserMapper {

	public static UserDto convert(User user) {
		return new UserDto().setId(user.getId()).setEmail(user.getEmail()).setFirstName(user.getFirstName())
				.setLastName(user.getLastName()).setMobileNumber(user.getMobileNumber())
				.setRoles(new HashSet<RoleDto>(user.getRoles().stream()
						.map(role -> new ModelMapper().map(role, RoleDto.class)).collect(Collectors.toSet())));
	}
}
