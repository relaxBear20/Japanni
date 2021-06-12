package com.ursa.app.timesheet.model.dto.mapper;

import java.util.HashSet;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.ursa.app.timesheet.model.dto.RoleDto;
import com.ursa.app.timesheet.model.dto.ShiftDto;
import com.ursa.app.timesheet.model.dto.UserDto;
import com.ursa.app.timesheet.model.dto.WorkDto;
import com.ursa.app.timesheet.model.dto.WorkShiftDto;
import com.ursa.app.timesheet.model.entity.User;
import com.ursa.app.timesheet.model.entity.Work;
import com.ursa.app.timesheet.model.entity.WorkShift;

@Component
public class WorkMapper {
	public static WorkDto convert(Work work) {
        return new WorkDto().setId(work.getId()).setName(work.getName());
    }
}
