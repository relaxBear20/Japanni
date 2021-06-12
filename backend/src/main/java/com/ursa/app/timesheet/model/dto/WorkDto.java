package com.ursa.app.timesheet.model.dto;

import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ursa.app.timesheet.model.entity.User;
import com.ursa.app.timesheet.model.entity.WorkShift;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkDto {
	private Long id;
	private String name;
	private Collection<WorkShiftDto> shifts;
}
