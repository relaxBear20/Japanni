package com.ursa.app.timesheet.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDto {
	@JsonProperty("access_token")
    private String accessToken;
}
