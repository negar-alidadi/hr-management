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
        public void save(LeaveRequest leaveRequest,Long employeeId) throws SQLException {
            String seqSQL = "select request_seq.nextval id from dual";
            String insertSQL = "insert into leave_request (id, employeeId, startDate, endDate, approved) values (?,?,?,?,?)";

            try (Connection connection = JDBC.getConnection();
                 PreparedStatement seqStmt = connection.prepareStatement(seqSQL);
                 ResultSet resultSet = seqStmt.executeQuery()) {
                resultSet.next();
                leaveRequest.setId(resultSet.getLong("id"));


                try (PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {
                    insertStmt.setLong(1, leaveRequest.getId());
                    insertStmt.setLong(2, employeeId);
                    insertStmt.setDate(3, leaveRequest.getStartDate());
                    insertStmt.setDate(4, leaveRequest.getEndDate());
                    insertStmt.setBoolean(5, leaveRequest.isApproved());
                    insertStmt.executeUpdate();
                }
            }
        }
//        try(Connection connection = JDBC.getConnection();
//            PreparedStatement preparedStatement= connection
//                    .prepareStatement("select request_seq.nextval id from dual")){
//            ResultSet resultSet = preparedStatement.executeQuery();
//            resultSet.next();
//            leaveRequest.setId(resultSet.getLong("id"));
//            connection.prepareStatement(("insert into leave_request (id,employeeId,startDate,endDate,approved) values (?,?,?,?,?)"));
//        preparedStatement.setLong(1, leaveRequest.getId());
//        preparedStatement.setLong(2, leaveRequest.getEmployee().getId());
//        preparedStatement.setDate(3,leaveRequest.getStartDate());
//        preparedStatement.setDate(4,leaveRequest.getEndDate());
//        preparedStatement.setBoolean(5,leaveRequest.isApproved());
//        preparedStatement.executeQuery();
//
//    }}

    @Override
    public List<LeaveRequest> findAll() throws SQLException {
        try(Connection connection = JDBC.getConnection();
            PreparedStatement preparedStatement=  connection.prepareStatement(("select * from leave_request"))){
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        Employee employee = employeeRepository.findEmployeeById(resultSet.getLong("employeeId"));
        List<LeaveRequest> leaveRequests = new ArrayList<>();
        while (resultSet.next()) {
            LeaveRequest leaveRequest = new LeaveRequest();
            leaveRequest.setId(resultSet.getLong("id"));
            leaveRequest.setEmployee(employee);
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
            while (resultSet.next()) {
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
    public void update(Long id,LeaveRequest leaveRequest) throws SQLException {
        try(Connection connection = JDBC.getConnection();
            PreparedStatement preparedStatement=  connection.prepareStatement(("update leave_request set employeeId = ?, startDate = ?, endDate = ?, approved = ? where id = ?"))){
        preparedStatement.setLong(1, leaveRequest.getEmployee().getId());
        preparedStatement.setDate(2, leaveRequest.getStartDate());
        preparedStatement.setDate(3, leaveRequest.getEndDate());
        preparedStatement.setBoolean(4, leaveRequest.isApproved());
        preparedStatement.setLong(5, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No employee found with id: " + id);
            } else {
                System.out.println("Employee with id " + id + " updated successfully.");
            }
    }}

    @Override
    public void delete(Long id) throws SQLException {
        try(Connection connection = JDBC.getConnection();
            PreparedStatement preparedStatement=  connection.prepareStatement("delete from leave_request where id = ?")){
        preparedStatement.setLong(1, id);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 0) {
                System.out.println("No employee found with id: " + id);
            } else {
                System.out.println("Employee with id " + id + " updated successfully.");
            }
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
