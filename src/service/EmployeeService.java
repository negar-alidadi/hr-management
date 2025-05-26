package service;

import model.Employee;
import repository.EmployeeRepositoryImpl;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl();

       public EmployeeService(EmployeeRepositoryImpl employeeRepository) {
           this.employeeRepository = employeeRepository;
       }

       public EmployeeService() {}

    public Employee getEmployeeById(Long id) throws SQLException {
        return employeeRepository.findEmployeeById(id);
    }
    public Employee add(Employee employee) throws SQLException {
           employeeRepository.save(employee);
           return employee;
    }
    public void update(Long id,Employee employee) throws SQLException {
           employeeRepository.update(id,employee);
    }
    public void delete(Long id) throws SQLException {
           employeeRepository.delete(id);
    }
    public List<Employee> getAllEmployees() throws SQLException{
           return employeeRepository.findAll();
    }
}
