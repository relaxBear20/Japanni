package com.ursa.app.timesheet.controller.v1.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserLoginRequest {
	 private String username;
	 private String password;
}
