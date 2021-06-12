package com.ursa.app.timesheet.controller.v1.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ursa.app.timesheet.controller.v1.request.WorkAddRequest;
import com.ursa.app.timesheet.controller.v1.request.WorkListRequest;
import com.ursa.app.timesheet.model.dto.ShiftDto;
import com.ursa.app.timesheet.service.ShiftService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/v1")
public class ShiftController {
	@Autowired
	ShiftService shiftService;

	/**
	 * Handles the incoming GET API "/v1/shift/all"
	 * 
	 * @return
	 */
	@GetMapping("/shift/all")
	public ResponseEntity<?> getAllWorks() {
		return ResponseEntity.ok().body(shiftService.allShifts());
	}

	/**
	 * Handles the incoming GET API "/v1/shift"
	 * 
	 * @return
	 */
	@GetMapping("/shift")
	public ResponseEntity<?> search(@Valid @ModelAttribute WorkListRequest request) {
		return ResponseEntity.ok().body(shiftService.search(request.getSearch(), request.getPagable()));
	}

	/**
	 * Handles the incoming POST API "/v1/shift"
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/shift")
	public ResponseEntity<?> addWorks(@Valid @RequestBody WorkAddRequest request) {
		ShiftDto work = new ShiftDto().setName(request.getName());
		return ResponseEntity.ok().body(shiftService.add(work));
	}

	/**
	 * Handles the incoming GET API "/v1/shift/{id}"
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/shift/{id}")
	public ResponseEntity<?> getWork(@PathVariable(value = "id") Long id) {

		return ResponseEntity.ok().body(shiftService.get(id));
	}

	/**
	 * Handles the incoming POST API "/v1/shift/{id}"
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@PostMapping("/shift/{id}")
	public ResponseEntity<?> editWork(@PathVariable(value = "id") Long id, @Valid @RequestBody WorkAddRequest request) {
		ShiftDto work = new ShiftDto().setName(request.getName());
		return ResponseEntity.ok().body(shiftService.edit(id, work));
	}

	/**
	 * Handles the incoming DELETE API "/v1/shift/{id}"
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/shift/{id}")
	public ResponseEntity<?> deleteWork(@PathVariable(value = "id") Long id) {
		return ResponseEntity.ok().body(shiftService.delete(id));
	}
}
