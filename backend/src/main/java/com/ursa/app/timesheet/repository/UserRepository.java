package com.ursa.app.timesheet.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ursa.app.timesheet.model.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String username);
    User findByEmail(String email);
    
    @Query("select u from User u where :search is null or u.username like concat('%',:search,'%')")
    Page<User> search(@Param("search") String search, Pageable pageable);
    
}
