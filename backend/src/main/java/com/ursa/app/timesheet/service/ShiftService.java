package com.ursa.app.timesheet.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ursa.app.timesheet.model.dto.ShiftDto;
import com.ursa.app.timesheet.model.dto.ShiftDto;

public interface ShiftService {
	/**
	 * 
	 * @param work
	 * @return
	 */
	ShiftDto add(ShiftDto work);

	/**
	 * 
	 * @param id
	 * @param work
	 * @return
	 */
	ShiftDto edit(Long id, ShiftDto workData);

	/**
	 * 
	 * @param id
	 * @return
	 */
	ShiftDto get(Long id);

	/**
	 * 
	 * @param id
	 * @return
	 */
	ShiftDto delete(Long id);

	/**
	 * 
	 * @param search
	 * @param pageable
	 * @return
	 */
	Page<ShiftDto> search(String search, Pageable pageable);
	
	/**
	 * get all works
	 * @return
	 */
	List<ShiftDto> allShifts();
	
}
