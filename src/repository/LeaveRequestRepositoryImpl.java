package repository;

import common.JDBC;
import dto.LeaveRequestDto;
import exception.SqlConnectionEx;
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

    public final EmployeeRepository employeeRepository;

    public LeaveRequestRepositoryImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void save(LeaveRequest leaveRequest, Long employeeId) {
        String seqSQL = "SELECT request_seq.NEXTVAL id FROM dual";
        String insertSQL = "INSERT INTO leave_request (id, employeeId, startDate, endDate, approved) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement seqStmt = connection.prepareStatement(seqSQL);
             ResultSet resultSet = seqStmt.executeQuery()) {

            if (resultSet.next()) {
                leaveRequest.setId(resultSet.getLong("id"));
            } else {
                throw new SqlConnectionEx("Could not get sequence value from DB");
            }

            try (PreparedStatement insertStmt = connection.prepareStatement(insertSQL)) {
                insertStmt.setLong(1, leaveRequest.getId());
                insertStmt.setLong(2, employeeId);
                insertStmt.setDate(3, leaveRequest.getStartDate());
                insertStmt.setDate(4, leaveRequest.getEndDate());
                insertStmt.setBoolean(5, leaveRequest.isApproved());
                insertStmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new SqlConnectionEx("Database connection failed: " + e.getMessage());
        }
    }

    @Override
    public List<LeaveRequest> findAll() {
        String sql = "SELECT * FROM leave_request";
        List<LeaveRequest> leaveRequests = new ArrayList<>();

        try (Connection connection = JDBC.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LeaveRequest leaveRequest = new LeaveRequest();
                leaveRequest.setId(rs.getLong("id"));
                Long empId = rs.getLong("employeeId");
                Employee employee = employeeRepository.findEmployeeById(empId);
                leaveRequest.setEmployee(employee);
                leaveRequest.setStartDate(rs.getDate("startDate"));
                leaveRequest.setEndDate(rs.getDate("endDate"));
                leaveRequest.setApproved(rs.getBoolean("approved"));
                leaveRequests.add(leaveRequest);
            }
            return leaveRequests;

        } catch (SQLException e) {
            throw new SqlConnectionEx("Database connection failed: " + e.getMessage());
        }
    }

    @Override
    public LeaveRequest findById(Long id) {
        String sql = "SELECT * FROM leave_request WHERE id = ?";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                LeaveRequest leaveRequest = new LeaveRequest();
                leaveRequest.setId(rs.getLong("id"));
                Employee employee = employeeRepository.findEmployeeById(rs.getLong("employeeId"));
                leaveRequest.setEmployee(employee);
                leaveRequest.setStartDate(rs.getDate("startDate"));
                leaveRequest.setEndDate(rs.getDate("endDate"));
                leaveRequest.setApproved(rs.getBoolean("approved"));
                return leaveRequest;
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new SqlConnectionEx("Database connection failed: " + e.getMessage());
        }
    }

    @Override
    public void update(Long id, LeaveRequest leaveRequest) {
        String sql = "UPDATE leave_request SET employeeId = ?, startDate = ?, endDate = ?, approved = ? WHERE id = ?";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, leaveRequest.getEmployee().getId());
            ps.setDate(2, leaveRequest.getStartDate());
            ps.setDate(3, leaveRequest.getEndDate());
            ps.setBoolean(4, leaveRequest.isApproved());
            ps.setLong(5, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No LeaveRequest found with id: " + id);
            }

        } catch (SQLException e) {
            throw new SqlConnectionEx("Database connection failed: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM leave_request WHERE id = ?";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setLong(1, id);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No LeaveRequest found with id: " + id);
            }

        } catch (SQLException e) {
            throw new SqlConnectionEx("Database connection failed: " + e.getMessage());
        }
    }
}


