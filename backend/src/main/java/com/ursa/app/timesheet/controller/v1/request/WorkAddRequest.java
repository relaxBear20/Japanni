package com.ursa.app.timesheet.controller.v1.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WorkAddRequest {
	@NotEmpty(message = "name.empty")
	@NotNull(message = "name.null")
	private String name;
}
