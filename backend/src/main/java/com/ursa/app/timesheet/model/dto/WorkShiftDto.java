package com.ursa.app.timesheet.model.dto;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class WorkShiftDto {
	private Long id;

	private WorkDto work;

	private ShiftDto shift;
}
