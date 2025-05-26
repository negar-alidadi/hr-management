package repository;

import common.JDBC;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Override
    public Employee save(Employee employee) throws SQLException {
        String seqSQL = "select employee_seq.nextval AS id from dual";
        String insertSQL = "insert into employees (id, firstName, lastName, nationalId, employeeId) values (?,?,?,?,?)";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement seqStmt = connection.prepareStatement(seqSQL);
             ResultSet resultSet = seqStmt.executeQuery()) {

             resultSet.next();
             employee.setId(resultSet.getLong("id"));


            try (PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {
                insertStmt.setLong(1, employee.getId());
                insertStmt.setString(2, employee.getFirstName());
                insertStmt.setString(3, employee.getLastName());
                insertStmt.setInt(4, employee.getNationalId());
                insertStmt.setString(5, employee.getEmployeeId());
                insertStmt.executeUpdate();
            }
        }
        return employee;
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        try(Connection connection = JDBC.getConnection();
            PreparedStatement preparedStatement=  connection.prepareStatement(("select * from employees"))){
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()) {
            Employee employee = new Employee();
            employee.setId(resultSet.getLong("id"));
            employee.setFirstName(resultSet.getString("firstName"));
            employee.setLastName(resultSet.getString("lastName"));
            employee.setNationalId(resultSet.getInt("nationalId"));
            employee.setEmployeeId(resultSet.getString("employeeId"));
            employeeList.add(employee);
        }
        return employeeList;
            }
    }

    @Override
    public void delete(Long id) throws SQLException {
        try(Connection connection = JDBC.getConnection();
            PreparedStatement preparedStatement=  connection.prepareStatement(("delete from employees where id=?"))){
        preparedStatement.setLong(1, id);
        preparedStatement.executeUpdate();
    }}

    @Override
    public void update(Long id, Employee employee) throws SQLException {
        try(Connection connection = JDBC.getConnection();
            PreparedStatement preparedStatement=  connection.prepareStatement(("update employees set firstName=?, lastName=?, nationalId=?,dmployeeId=? where id=?"))){
        preparedStatement.setString(1, employee.getFirstName());
        preparedStatement.setString(2, employee.getLastName());
        preparedStatement.setInt(3, employee.getNationalId());
        preparedStatement.setString(4, employee.getEmployeeId());
        preparedStatement.setLong(5, id);
        int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No employee found with id: " + id);
            } else {
                System.out.println("Employee with id " + id + " updated successfully.");
            }
    }}

    @Override
    public Employee findEmployeeById(Long id) throws SQLException {
        try(Connection connection = JDBC.getConnection();
            PreparedStatement preparedStatement=  connection.prepareStatement(("select * from employees where id=?"))){
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
            Employee employee = new Employee();
        while (resultSet.next()) {
            employee.setId(resultSet.getLong("id"));
            employee.setFirstName(resultSet.getString("firstName"));
            employee.setLastName(resultSet.getString("lastName"));
            employee.setNationalId(resultSet.getInt("nationalId"));
            employee.setEmployeeId(resultSet.getString("employeeId"));
        }
            return employee;
        }
    }

}
