package repository;

import dto.LeaveRequestDto;
import model.LeaveRequest;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface LeaveRequestRepository  {
     void save(LeaveRequest leaveRequest,Long employeeId) ;
     List<LeaveRequest> findAll() ;
     LeaveRequest findById(Long id) ;
     void update(Long id,LeaveRequest leaveRequest) ;
     void delete(Long id) ;
}
