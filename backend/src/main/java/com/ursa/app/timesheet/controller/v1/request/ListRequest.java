package com.ursa.app.timesheet.controller.v1.request;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListRequest {
	private Integer page;
	private Integer size;
	private String sortBy;
	private boolean sortDesc;
	
	public Pageable getPagable() {
		if(page == null) page = 1;
		if(size == null) size = 10;
		
		if(sortBy != null && !sortBy.equals("")) {
			Sort sort = sortDesc ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
			return PageRequest.of(page - 1, size, sort);
		}
		return PageRequest.of(page - 1, size);
	}
}
