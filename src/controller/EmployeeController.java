package controller;

import model.Employee;
import service.EmployeeService;

import java.sql.SQLException;
import java.util.List;

public class EmployeeController {
    private EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    public EmployeeController(){}

    public Employee searchEmployee(Long id) throws SQLException {
      return   employeeService.getEmployeeById(id);
    }
    public Employee insertEmployee(Employee employee) throws SQLException {
        return employeeService.add(employee);
    }
    public void editEmployee(Long id,Employee employee) throws SQLException {
        employeeService.update(id,employee);
    }
    public void removeEmployee(Long id) throws SQLException {
        employeeService.delete(id);
    }
    public List<Employee> getAllEmployees() throws SQLException {
        return employeeService.getAllEmployees();
    }

}
