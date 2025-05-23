package repository;

import common.JDBC;
import dto.LeaveRequestDto;
import model.Employee;
import model.LeaveRequest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LeaveRequestRepositoryImpl implements LeaveRequestRepository {

    private EmployeeRepositoryImpl employeeRepository;
    public LeaveRequestRepositoryImpl(EmployeeRepositoryImpl employeeRepository)  {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void save(LeaveRequest leaveRequest) throws SQLException {
        try(Connection connection = JDBC.getConnection();
            PreparedStatement preparedStatement=  connection.prepareStatement(("insert into leave_request (id,employeeId,startDate,endDate,approved) values (?,?,?,?,?)"))){
        preparedStatement.setLong(1, leaveRequest.getId());
        preparedStatement.setLong(2, leaveRequest.getEmployee().getId());
        preparedStatement.setDate(3,leaveRequest.getStartDate());
        preparedStatement.setDate(4,leaveRequest.getEndDate());
        preparedStatement.setBoolean(5,leaveRequest.isApproved());
        preparedStatement.executeQuery();

    }}

    @Override
    public List<LeaveRequest> findAll() throws SQLException {
        try(Connection connection = JDBC.getConnection();
            PreparedStatement preparedStatement=  connection.prepareStatement(("select * from leave_request"))){
        ResultSet resultSet = preparedStatement.executeQuery();
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        while (resultSet.next()) {
            LeaveRequest leaveRequest = new LeaveRequest();
            leaveRequest.setId(resultSet.getLong("id"));
            leaveRequest.setStartDate(resultSet.getDate("startDate"));
            leaveRequest.setEndDate(resultSet.getDate("endDate"));
            leaveRequest.setApproved(resultSet.getBoolean("approved"));
            leaveRequests.add(leaveRequest);
        }
        return leaveRequests;
    }}

    @Override
    public LeaveRequest findById(Long id) throws SQLException {
        try(Connection connection = JDBC.getConnection();
            PreparedStatement preparedStatement=  connection.prepareStatement("select * from leave_request where id = ?")){
        {
            preparedStatement.setLong(1, id);
            LeaveRequest leaveRequest = new LeaveRequest();
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                leaveRequest.setId(resultSet.getLong("id"));
                Long employeeId = resultSet.getLong("employeeId");
                Employee employee = employeeRepository.findEmployeeById(employeeId);
                leaveRequest.setEmployee(employee);
                leaveRequest.setStartDate(resultSet.getDate("startDate"));
                leaveRequest.setEndDate(resultSet.getDate("endDate"));
                leaveRequest.setApproved(resultSet.getBoolean("approved"));
            }
            return leaveRequest;
        }}
    }

    @Override
    public void update(LeaveRequest leaveRequest) throws SQLException {
        try(Connection connection = JDBC.getConnection();
            PreparedStatement preparedStatement=  connection.prepareStatement(("update leave_request set employeeId = ?, startDate = ?, endDate = ?, approved = ? where id = ?"))){
        preparedStatement.setLong(1, leaveRequest.getEmployee().getId());
        preparedStatement.setDate(2, leaveRequest.getStartDate());
        preparedStatement.setDate(3, leaveRequest.getEndDate());
        preparedStatement.setBoolean(4, leaveRequest.isApproved());
        preparedStatement.setLong(5, leaveRequest.getId());
        preparedStatement.executeUpdate();
    }}

    @Override
    public void delete(LeaveRequest leaveRequest) throws SQLException {
        try(Connection connection = JDBC.getConnection();
            PreparedStatement preparedStatement=  connection.prepareStatement("delete from leave_request where id = ?")){
        preparedStatement.setLong(1, leaveRequest.getId());
        preparedStatement.executeUpdate();
    }}

//    @Override
//    public void commit() throws SQLException {
//        connection.commit();
//
//    }
//
//    @Override
//    public void close() throws Exception {
//        preparedStatement.close();
//        connection.close();
//
//    }
}
