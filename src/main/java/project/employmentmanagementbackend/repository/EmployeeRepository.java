package project.employmentmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.employmentmanagementbackend.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
