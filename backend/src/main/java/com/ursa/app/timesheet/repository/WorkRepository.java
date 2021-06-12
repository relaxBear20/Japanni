package com.ursa.app.timesheet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.ursa.app.timesheet.model.entity.Work;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

	@Query("select w from Work w where :search is null or w.name like concat('%',:search,'%')")
	Page<Work> search(@Param("search") String search, Pageable pageable);

	void deleteByName(String name);

	Work findByName(String name);
}
