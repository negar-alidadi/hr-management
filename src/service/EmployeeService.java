package service;

import model.Employee;
import repository.EmployeeRepository;
import repository.EmployeeRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    EmployeeRepository employeeRepository;

       public EmployeeService(EmployeeRepository employeeRepository) {
           this.employeeRepository = employeeRepository;
       }

       public EmployeeService() {}

    public Employee getEmployeeById(Long id)  {
        return employeeRepository.findEmployeeById(id);
    }
    public Employee add(Employee employee)  {
           employeeRepository.save(employee);
           return employee;
    }
    public void update(Long id,Employee employee)  {
           employeeRepository.update(id,employee);
    }
    public void delete(Long id)  {
           employeeRepository.delete(id);
    }
    public List<Employee> getAllEmployees() {
           return employeeRepository.findAll();
    }
}
