package com.ursa.app.timesheet.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ursa.app.timesheet.model.dto.ShiftDto;
import com.ursa.app.timesheet.model.dto.WorkDto;
import com.ursa.app.timesheet.model.dto.WorkShiftDto;

public interface WorkService {
	/**
	 * 
	 * @param work
	 * @return
	 */
	WorkDto add(WorkDto work);

	/**
	 * 
	 * @param id
	 * @param work
	 * @return
	 */
	WorkDto edit(Long id, WorkDto workData);

	/**
	 * 
	 * @param id
	 * @return
	 */
	WorkDto get(Long id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	WorkDto delete(Long id);

	/**
	 * 
	 * @param search
	 * @param pageable
	 * @return
	 */
	Page<WorkDto> search(String search, Pageable pageable);

	/**
	 * get all works
	 * 
	 * @return
	 */
	List<WorkDto> allWorks();

	/**
	 * add Shift to Work by id
	 * 
	 * @param id
	 * @param shiftId
	 * @return
	 */
	WorkDto addShift(Long id, Long shiftId);

	/**
	 * 
	 * @param search
	 * @param pageable
	 * @return
	 */
	Page<ShiftDto> searchShift(String search, Long workid, Pageable pageable);

	/**
	 * get all works
	 * 
	 * @return
	 */
	List<ShiftDto> allShifts(Long workid);
}
