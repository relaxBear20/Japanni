package com.ursa.app.timesheet.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ursa.app.timesheet.model.dto.UserDto;
import com.ursa.app.timesheet.model.dto.UserWorkDto;
import com.ursa.app.timesheet.model.dto.WorkDto;
import com.ursa.app.timesheet.model.entity.Work;

public interface UserService {
	/**
	 * Register a new user
	 *
	 * @param userDto
	 * @return
	 */
	UserDto signup(UserDto userDto);

	/**
	 * Search an existing user
	 *
	 * @param username
	 * @return
	 */
	UserDto findUserByUsername(String username);

	/**
	 * Search an existing user
	 *
	 * @param email
	 * @return
	 */
	UserDto findUserByEmail(String email);

	/**
	 * Update profile of the user
	 *
	 * @param userDto
	 * @return
	 */
	UserDto updateProfile(UserDto userDto);

	/**
	 * Update password
	 *
	 * @param newPassword
	 * @return
	 */
	UserDto changePassword(UserDto userDto, String newPassword);

	/**
	 * Search user
	 * 
	 * @param search
	 * @param pageable
	 * @return
	 */
	Page<UserDto> search(String search, Pageable pageable);

	/**
	 * Get all work from user id
	 * 
	 * @param id
	 * @return
	 */
	List<WorkDto> getWorks(Long id);

	/**
	 * Add work to user
	 * 
	 * @param id
	 * @param work
	 * @return
	 */
	UserWorkDto addWork(Long id, Long workId);

	/**
	 * Delete Work from user
	 * @param userId
	 * @param workId
	 * @return
	 */
	UserWorkDto deleteWork(Long userId, Long workId);
}
