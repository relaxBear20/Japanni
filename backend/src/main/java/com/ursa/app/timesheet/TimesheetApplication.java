package com.ursa.app.timesheet;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ursa.app.timesheet.model.entity.Role;
import com.ursa.app.timesheet.model.entity.User;
import com.ursa.app.timesheet.model.entity.UserRoles;
import com.ursa.app.timesheet.repository.RoleRepository;
import com.ursa.app.timesheet.repository.UserRepository;

@SpringBootApplication
public class TimesheetApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimesheetApplication.class, args);
    }

    @Bean
    CommandLineRunner init(RoleRepository roleRepository, UserRepository userRepository) {
        return args -> {
            //Create Admin and Passenger Roles
            Role adminRole = roleRepository.findByRole(UserRoles.ADMIN);
            if (adminRole == null) {
                adminRole = new Role();
                adminRole.setRole(UserRoles.ADMIN);
                roleRepository.save(adminRole);
            }

            Role userRole = roleRepository.findByRole(UserRoles.EMPLOYEE);
            if (userRole == null) {
                userRole = new Role();
                userRole.setRole(UserRoles.EMPLOYEE);
                roleRepository.save(userRole);
            }

            //Create an Admin user
            User admin = userRepository.findByEmail("admin@gmail.com");
            if (admin == null) {
                admin = new User()
                		.setUsername("admin")
                        .setEmail("admin@gmail.com")
                        .setPassword("$2a$10$7PtcjEnWb/ZkgyXyxY1/Iei2dGgGQUbqIIll/dt.qJ8l8nQBWMbYO") // "123456"
                        .setFirstName("John")
                        .setLastName("Doe")
                        .setMobileNumber("9425094250")
                        .setRoles(Arrays.asList(adminRole));
                userRepository.save(admin);
            }

            //Create a passenger user
            User passenger = userRepository.findByEmail("employee@gmail.com");
            if (passenger == null) {
                passenger = new User()
                		.setUsername("employee")
                        .setEmail("employee@gmail.com")
                        .setPassword("$2a$10$7PtcjEnWb/ZkgyXyxY1/Iei2dGgGQUbqIIll/dt.qJ8l8nQBWMbYO") // "123456"
                        .setFirstName("Mira")
                        .setLastName("Jane")
                        .setMobileNumber("8000110008")
                        .setRoles(Arrays.asList(userRole));
                userRepository.save(passenger);
            }
        };
    }
}
