package com.ursa.app.timesheet.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ursa.app.timesheet.model.entity.Shift;
import com.ursa.app.timesheet.model.entity.Work;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
	@Query("select s from Shift s where :search is null or s.name like concat('%',:search,'%')")
	Page<Shift> search(@Param("search") String search, Pageable pageable);

	@Query("SELECT s FROM Shift s JOIN s.works sw WHERE sw.work IN :works AND "
			+ " :search IS null OR s.name LIKE concat('%',:search,'%')")
	Page<Shift> searchByWork(@Param("search") String search, @Param("works") List<Work> work, Pageable pageable);

	@Query("SELECT s FROM Shift s JOIN s.works sw WHERE sw.work IN :works")
	List<Shift> allShiftByWork(@Param("works") List<Work> work);

	Shift findByName(String name);
}
