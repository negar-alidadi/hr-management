package controller;

import exception.SqlConnectionEx;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import model.Employee;
import service.EmployeeService;

import java.util.List;
@Dependent
public class EmployeeController {
    private EmployeeService employeeService;
    @Inject
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    public EmployeeController(){}

    public Employee searchEmployee(Long id)throws SqlConnectionEx   {
      return   employeeService.getEmployeeById(id);
    }
    public Employee insertEmployee(Employee employee) throws SqlConnectionEx  {
        return employeeService.add(employee);
    }
    public void editEmployee(Long id,Employee employee) throws SqlConnectionEx  {
        employeeService.update(id,employee);
    }
    public void removeEmployee(Long id) throws SqlConnectionEx  {
        employeeService.delete(id);
    }
    public List<Employee> getAllEmployees() throws SqlConnectionEx {
        return employeeService.getAllEmployees();
    }

}
