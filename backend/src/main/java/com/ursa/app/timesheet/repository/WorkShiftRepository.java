package com.ursa.app.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ursa.app.timesheet.model.entity.WorkShift;

@Repository
public interface WorkShiftRepository extends JpaRepository<WorkShift, Long> {

}
