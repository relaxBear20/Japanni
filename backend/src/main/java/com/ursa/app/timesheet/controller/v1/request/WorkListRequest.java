package com.ursa.app.timesheet.controller.v1.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class WorkListRequest extends ListRequest {
	private String search;
	
}
