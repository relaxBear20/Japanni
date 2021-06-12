package com.ursa.app.timesheet.model.dto.mapper;
import org.springframework.stereotype.Component;

import com.ursa.app.timesheet.model.dto.ShiftDto;
import com.ursa.app.timesheet.model.entity.Shift;

@Component
public class ShiftMapper {
	public static ShiftDto convert(Shift shift) {
        return new ShiftDto().setId(shift.getId()).setName(shift.getName());
    }
}
