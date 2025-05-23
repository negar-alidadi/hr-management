package repository;

import dto.LeaveRequestDto;
import model.LeaveRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface LeaveRequestRepository  {
     void save(LeaveRequest leaveRequest) throws SQLException;
     List<LeaveRequest> findAll() throws SQLException;
     LeaveRequest findById(Long id) throws SQLException;
     void update(LeaveRequest leaveRequest) throws SQLException;
     void delete(LeaveRequest leaveRequest) throws SQLException;
   //  void commit() throws SQLException;
}
