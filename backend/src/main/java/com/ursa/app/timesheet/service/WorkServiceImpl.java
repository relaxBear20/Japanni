package com.ursa.app.timesheet.service;

import java.util.ArrayList;
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
import com.ursa.app.timesheet.model.dto.WorkDto;
import com.ursa.app.timesheet.model.dto.WorkShiftDto;
import com.ursa.app.timesheet.model.dto.mapper.ShiftMapper;
import com.ursa.app.timesheet.model.dto.mapper.WorkMapper;
import com.ursa.app.timesheet.model.dto.mapper.WorkShiftMapper;
import com.ursa.app.timesheet.model.entity.Shift;
import com.ursa.app.timesheet.model.entity.Work;
import com.ursa.app.timesheet.model.entity.WorkShift;
import com.ursa.app.timesheet.repository.ShiftRepository;
import com.ursa.app.timesheet.repository.WorkRepository;
import com.ursa.app.timesheet.repository.WorkShiftRepository;

@Component
public class WorkServiceImpl implements WorkService {
	@Autowired
	private WorkRepository workRepository;

	@Autowired
	private ShiftRepository shiftRepository;

	@Autowired
	private WorkShiftRepository workShiftRepository;

	@Override
	public WorkDto add(WorkDto data) {
		Work work = workRepository.findByName(data.getName());
		if (work != null) {
			throw exception(EntityType.WORK, ExceptionType.DUPLICATE_ENTITY, data.getName());
		}
		return WorkMapper.convert(workRepository.save(new Work().setName(data.getName())));
	}

	@Override
	public WorkDto edit(Long id, WorkDto workData) {
		Work work = workRepository.findById(id).get();
		if (work == null)
			throw exception(EntityType.WORK, ExceptionType.ENTITY_NOT_FOUND, id.toString());
		work.setName(workData.getName());
		return WorkMapper.convert(workRepository.save(work));
	}

	@Override
	public WorkDto get(Long id) {
		Work work = workRepository.findById(id).get();

		if (work == null)
			throw exception(EntityType.WORK, ExceptionType.ENTITY_NOT_FOUND, id.toString());

		return WorkMapper.convert(work);
	}

	@Override
	public WorkDto delete(Long id) {
		Work work = workRepository.findById(id).get();

		if (work == null)
			throw exception(EntityType.WORK, ExceptionType.ENTITY_NOT_FOUND, id.toString());
		workRepository.deleteById(id);
		return WorkMapper.convert(work);
	}

	@Override
	public Page<WorkDto> search(String search, Pageable pageable) {
		Page<Work> works = workRepository.search(search, pageable);
		return works.map(WorkMapper::convert);
	}

	@Override
	public List<WorkDto> allWorks() {
		List<Work> works = workRepository.findAll();
		return works.stream().map(WorkMapper::convert).collect(Collectors.toList());
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

	@Override
	public WorkDto addShift(Long id, Long shiftId) {
		Work work = workRepository.findById(id).get();

		if (work == null)
			throw exception(EntityType.WORK, ExceptionType.ENTITY_NOT_FOUND, id.toString());

		Shift shift = shiftRepository.findById(id).get();

		if (shift == null)
			throw exception(EntityType.SHIFT, ExceptionType.ENTITY_NOT_FOUND, shiftId.toString());

		WorkShift workShift = new WorkShift();
		workShift.setWork(work);
		workShift.setShift(shift);
		workShiftRepository.save(workShift);
		return WorkMapper.convert(work);
	}


	@Override
	public List<ShiftDto> allShifts(Long workid) {
		Work work = workRepository.findById(workid).get();

		if (work == null)
			throw exception(EntityType.WORK, ExceptionType.ENTITY_NOT_FOUND, workid.toString());
		List<Work> works = new ArrayList<Work>();
		works.add(work);
		List<Shift> shifts = shiftRepository.allShiftByWork( works);
		return shifts.stream().map(ShiftMapper::convert).collect(Collectors.toList());
	}

	@Override
	public Page<ShiftDto> searchShift(String search, Long workid, Pageable pageable) {
		Work work = workRepository.findById(workid).get();

		if (work == null)
			throw exception(EntityType.WORK, ExceptionType.ENTITY_NOT_FOUND, workid.toString());
		List<Work> works = new ArrayList<Work>();
		works.add(work);
		Page<Shift> shifts = shiftRepository.searchByWork(search, works, pageable);
		return shifts.map(ShiftMapper::convert);
	}

}
