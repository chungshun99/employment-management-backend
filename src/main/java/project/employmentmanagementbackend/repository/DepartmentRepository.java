package project.employmentmanagementbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.employmentmanagementbackend.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
