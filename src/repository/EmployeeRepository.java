package repository;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeRepository  {
    Employee save(Employee employee) throws Exception;
    List<Employee> findAll() throws SQLException;
    void delete(Long id) throws SQLException;
    void update(Long id,Employee employee) throws SQLException;
    Employee findEmployeeById(Long id) throws SQLException;
//    void close() throws SQLException;
//    void commit() throws SQLException;
}
