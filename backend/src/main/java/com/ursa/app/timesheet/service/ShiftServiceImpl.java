package com.ursa.app.timesheet.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.ursa.app.timesheet.exception.EntityType;
import com.ursa.app.timesheet.exception.ExceptionType;
import com.ursa.app.timesheet.exception.GlobalException;
import com.ursa.app.timesheet.model.dto.ShiftDto;
import com.ursa.app.timesheet.model.dto.mapper.ShiftMapper;
import com.ursa.app.timesheet.model.entity.Shift;
import com.ursa.app.timesheet.repository.ShiftRepository;
@Component
public class ShiftServiceImpl implements ShiftService{
	@Autowired
	ShiftRepository shiftRepository;

	@Override
	public ShiftDto add(ShiftDto data) {
		Shift shift = shiftRepository.findByName(data.getName());
		if (shift != null) {
			throw exception(EntityType.SHIFT, ExceptionType.DUPLICATE_ENTITY, data.getName());
		}
		return ShiftMapper.convert(shiftRepository.save(new Shift().setName(data.getName())));
	}

	@Override
	public ShiftDto edit(Long id, ShiftDto shiftData) {
		Shift shift = shiftRepository.findById(id).get();
		if (shift == null)
			throw exception(EntityType.SHIFT, ExceptionType.ENTITY_NOT_FOUND, id.toString());
		shift.setName(shiftData.getName());
		return ShiftMapper.convert(shiftRepository.save(shift));
	}

	@Override
	public ShiftDto get(Long id) {
		Shift shift = shiftRepository.findById(id).get();

		if (shift == null)
			throw exception(EntityType.SHIFT, ExceptionType.ENTITY_NOT_FOUND, id.toString());

		return ShiftMapper.convert(shift);
	}

	@Override
	public ShiftDto delete(Long id) {
		Shift shift = shiftRepository.findById(id).get();

		if (shift == null)
			throw exception(EntityType.SHIFT, ExceptionType.ENTITY_NOT_FOUND, id.toString());
		shiftRepository.deleteById(id);
		return ShiftMapper.convert(shift);
	}

	@Override
	public Page<ShiftDto> search(String search, Pageable pageable) {
		Page<Shift> shifts = shiftRepository.search(search, pageable);
		return shifts.map(ShiftMapper::convert);
	}

	@Override
	public List<ShiftDto> allShifts() {
		List<Shift> shifts = shiftRepository.findAll();
		return shifts.stream().map(ShiftMapper::convert).collect(Collectors.toList());
	}

	/**
	 * Returns a new RuntimeException
	 *
	 * @param entityType
	 * @param exceptionType
	 * @param args
	 * @return
	 */
	private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
		return GlobalException.throwException(entityType, exceptionType, args);
	}
}
