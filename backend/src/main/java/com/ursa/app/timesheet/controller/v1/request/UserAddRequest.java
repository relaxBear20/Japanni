package com.ursa.app.timesheet.controller.v1.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAddRequest {
	@NotEmpty(message = "username must be not empty")
	private String username;
	
    @NotEmpty(message = "email must be not empty")
    private String email;

    @NotEmpty(message = "password must be not empty")
    private String password;

    @NotEmpty(message = "first name must be not empty")
    private String firstName;

    @NotEmpty(message = "last name must be not empty")
    private String lastName;

    private String mobileNumber;
}
