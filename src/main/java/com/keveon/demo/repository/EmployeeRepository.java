package com.keveon.demo.repository;

import com.keveon.demo.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * The interface Employee repository.
 *
 * @author keveon on 2019/04/07.
 * @version 1.0.0
 * @since 1.0.0
 */
public interface EmployeeRepository
        extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {
}
