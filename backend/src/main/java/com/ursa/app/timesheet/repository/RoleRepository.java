package com.ursa.app.timesheet.repository;

import org.springframework.data.repository.CrudRepository;

import com.ursa.app.timesheet.model.entity.Role;
import com.ursa.app.timesheet.model.entity.UserRoles;

public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByRole(UserRoles role);

}
