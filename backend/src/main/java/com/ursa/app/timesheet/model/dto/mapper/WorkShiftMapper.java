package com.ursa.app.timesheet.model.dto.mapper;

import org.springframework.stereotype.Component;

import com.ursa.app.timesheet.model.dto.WorkDto;
import com.ursa.app.timesheet.model.dto.WorkShiftDto;
import com.ursa.app.timesheet.model.entity.Work;
import com.ursa.app.timesheet.model.entity.WorkShift;

@Component
public class WorkShiftMapper {
	public static WorkShiftDto convertWithShift(WorkShift data) {
		return new WorkShiftDto().setShift(ShiftMapper.convert(data.getShift()));
	}

	public static WorkShiftDto convertWithWork(WorkShift data) {
		return new WorkShiftDto().setWork(WorkMapper.convert(data.getWork()));
	}

	public static WorkShiftDto convertWithWorkShift(WorkShift data) {
		return new WorkShiftDto().setId(data.getId()).setShift(ShiftMapper.convert(data.getShift()))
				.setWork(WorkMapper.convert(data.getWork()));
	}
}
