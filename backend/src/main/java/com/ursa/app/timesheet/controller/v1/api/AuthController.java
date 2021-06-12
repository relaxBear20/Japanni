package com.ursa.app.timesheet.controller.v1.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ursa.app.timesheet.controller.v1.request.UserLoginRequest;
import com.ursa.app.timesheet.model.dto.LoginDto;
import com.ursa.app.timesheet.model.response.Response;
import com.ursa.app.timesheet.security.JwtUtils;

@RestController
@RequestMapping("/v1")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtUtils jwtUtils;
    
    /**
     * Handles the incoming POST API "/v1/login"
     *
     * @param userLoginRequest
     * @return
     */
    @PostMapping("/login")
    public Response<?> login(@RequestBody @Valid UserLoginRequest userLoginRequest) {
    	Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(userLoginRequest.getUsername(), userLoginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);		
		String token = jwtUtils.generateJwtToken(authentication);
        
    	return Response.ok().setPayload(new LoginDto(token));
    }
}
