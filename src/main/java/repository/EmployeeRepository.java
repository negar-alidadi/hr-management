package repository;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeRepository  {
    Employee save(Employee employee) ;
    List<Employee> findAll() ;
    void delete(Long id) ;
    void update(Long id,Employee employee) ;
    Employee findEmployeeById(Long id) ;

}
