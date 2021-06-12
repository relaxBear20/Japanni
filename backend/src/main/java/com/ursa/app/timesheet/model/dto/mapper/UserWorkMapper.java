package com.ursa.app.timesheet.model.dto.mapper;

import java.util.HashSet;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.ursa.app.timesheet.model.dto.UserWorkDto;
import com.ursa.app.timesheet.model.dto.WorkDto;
import com.ursa.app.timesheet.model.entity.User;

@Component
public class UserWorkMapper {

	public static UserWorkDto convert(User user) {
		return new UserWorkDto().setId(user.getId()).setEmail(user.getEmail()).setFirstName(user.getFirstName())
				.setLastName(user.getLastName()).setWorks(null).setWorks(new HashSet<WorkDto>(user.getWorks().stream()
						.map(work -> new ModelMapper().map(work, WorkDto.class)).collect(Collectors.toSet())));
				
	}
}
