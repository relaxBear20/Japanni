package com.ursa.app.timesheet.controller.v1.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ursa.app.timesheet.controller.v1.request.UserAddRequest;
import com.ursa.app.timesheet.controller.v1.request.UserAddWorkRequest;
import com.ursa.app.timesheet.controller.v1.request.UserListRequest;
import com.ursa.app.timesheet.model.dto.UserDto;
import com.ursa.app.timesheet.model.response.Response;
import com.ursa.app.timesheet.service.UserService;

@RestController
@RequestMapping("/v1")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Handles the incoming POST API "/v1/user"
	 *
	 * @param userSignupRequest
	 * @return
	 */
	@PostMapping("/user")
	public Response<?> addUser(@Valid @RequestBody UserAddRequest userAddRequest) {
		UserDto userDto = new UserDto().setEmail(userAddRequest.getEmail()).setPassword(userAddRequest.getPassword())
				.setFirstName(userAddRequest.getFirstName()).setLastName(userAddRequest.getLastName())
				.setMobileNumber(userAddRequest.getMobileNumber()).setAdmin(false);

		userDto = userService.signup(userDto);
		return Response.ok().setPayload(userDto);
	}

	@GetMapping("/user")
	public Page<UserDto> searchUser(@ModelAttribute UserListRequest request) {
		return userService.search(request.getSearch(), request.getPagable());
	}

	@GetMapping("/user/{id}/work")

	public ResponseEntity<?> getWorks(@PathVariable(value = "id") Long id) {
		return ResponseEntity.ok().body(userService.getWorks(id));
	}

	@PostMapping("/user/{id}/work")

	public ResponseEntity<?> addWork(@PathVariable(value = "id") Long id, @RequestBody UserAddWorkRequest request) {
		return ResponseEntity.ok().body(userService.addWork(id, request.getWorkId()));
	}
	@DeleteMapping("/user/{id}/work")
	public ResponseEntity<?> deleteWork(@PathVariable(value = "id") Long id, @RequestBody UserAddWorkRequest request) {
		return ResponseEntity.ok().body(userService.deleteWork(id, request.getWorkId()));
	}
}
