package repository;

import dto.LeaveRequestDto;
import model.LeaveRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface LeaveRequestRepository  {
     void save(LeaveRequest leaveRequest,Long employeeId) throws SQLException;
     List<LeaveRequest> findAll() throws SQLException;
     LeaveRequest findById(Long id) throws SQLException;
     void update(Long id,LeaveRequest leaveRequest) throws SQLException;
     void delete(Long id) throws SQLException;
   //  void commit() throws SQLException;
}
