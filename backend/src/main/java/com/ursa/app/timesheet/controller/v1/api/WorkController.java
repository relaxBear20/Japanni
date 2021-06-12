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
import com.ursa.app.timesheet.controller.v1.request.WorkAddShiftRequest;
import com.ursa.app.timesheet.controller.v1.request.WorkListRequest;
import com.ursa.app.timesheet.model.dto.WorkDto;
import com.ursa.app.timesheet.service.WorkService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/v1")
public class WorkController {
	@Autowired
	WorkService workService;

	/**
	 * Handles the incoming GET API "/v1/work/all"
	 * 
	 * @return
	 */
	@GetMapping("/work/all")
	public ResponseEntity<?> getAllWorks() {
		return ResponseEntity.ok().body(workService.allWorks());
	}

	/**
	 * Handles the incoming GET API "/v1/work"
	 * 
	 * @return
	 */
	@GetMapping("/work")
	public ResponseEntity<?> search(@Valid @ModelAttribute WorkListRequest request) {
		return ResponseEntity.ok().body(workService.search(request.getSearch(), request.getPagable()));
	}

	/**
	 * Handles the incoming POST API "/v1/work"
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/work")
	public ResponseEntity<?> addWorks(@Valid @RequestBody WorkAddRequest request) {
		WorkDto work = new WorkDto().setName(request.getName());
		return ResponseEntity.ok().body(workService.add(work));
	}

	/**
	 * Handles the incoming GET API "/v1/work/{id}"
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/work/{id}")
	public ResponseEntity<?> getWork(@PathVariable(value = "id") Long id) {

		return ResponseEntity.ok().body(workService.get(id));
	}

	/**
	 * Handles the incoming POST API "/v1/work/{id}"
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@PostMapping("/work/{id}")
	public ResponseEntity<?> editWork(@PathVariable(value = "id") Long id, @Valid @RequestBody WorkAddRequest request) {
		WorkDto work = new WorkDto().setName(request.getName());
		return ResponseEntity.ok().body(workService.edit(id, work));
	}

	/**
	 * Handles the incoming DELETE API "/v1/work/{id}"
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/work/{id}")
	public ResponseEntity<?> deleteWork(@PathVariable(value = "id") Long id) {
		return ResponseEntity.ok().body(workService.delete(id));
	}

	/**
	 * Handles the incoming POST API "/v1/work/{id}"
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@PostMapping("/work/{id}/shift")
	public ResponseEntity<?> addShift(@PathVariable(value = "id") Long id,
			@Valid @RequestBody WorkAddShiftRequest request) {

		return ResponseEntity.ok().body(workService.addShift(id, request.getShiftId()));
	}

	/**
	 * Handles the incoming GET API "/v1/work/{id}/all_shift"
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/work/{id}/all_shift")
	public ResponseEntity<?> allShifts(@PathVariable(value = "id") Long id) {

		return ResponseEntity.ok().body(workService.allShifts(id));
	}

	/**
	 * Handles the incoming GET API "/v1/work/{id}/shift"
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/work/{id}/shift")
	public ResponseEntity<?> searchShift(@PathVariable(value = "id") Long id,
			@Valid @ModelAttribute WorkListRequest request) {

		return ResponseEntity.ok().body(workService.searchShift(request.getSearch(), id, request.getPagable()));
	}
}
