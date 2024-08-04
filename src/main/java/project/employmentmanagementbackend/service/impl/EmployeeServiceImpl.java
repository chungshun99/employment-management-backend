package project.employmentmanagementbackend.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.employmentmanagementbackend.dto.EmployeeDto;
import project.employmentmanagementbackend.entity.Department;
import project.employmentmanagementbackend.entity.Employee;
import project.employmentmanagementbackend.exception.ResourceNotFoundException;
import project.employmentmanagementbackend.mapper.EmployeeMapper;
import project.employmentmanagementbackend.repository.DepartmentRepository;
import project.employmentmanagementbackend.repository.EmployeeRepository;
import project.employmentmanagementbackend.service.EmployeeService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository  employeeRepository;

    private DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        int[] nums = {4, 1, 0, 5};
        Arrays.stream(nums).map((num) -> num * num).sorted().toArray();

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);

        Department department = departmentRepository.findById(employeeDto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department id does not exists: " + employeeDto.getDepartmentId()));

        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee id does not exist: " + employeeId));

        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                    .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployee) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee id does not exist: " + employeeId));

        employee.setFirstName(updatedEmployee.getFirstName());
        employee.setLastName(updatedEmployee.getLastName());
        employee.setEmail(updatedEmployee.getEmail());

        Department department = departmentRepository.findById(updatedEmployee.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department id does not exists: " + updatedEmployee.getDepartmentId()));

        employee.setDepartment(department);


        Employee updatedEmployeeObj = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee id does not exist: " + employeeId));

        employeeRepository.deleteById(employeeId);
    }
}
