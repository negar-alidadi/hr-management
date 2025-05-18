package repository;

import dto.LeaveRequestDto;
import model.LeaveRequest;

import java.util.List;
import java.util.Optional;

public interface LeaveRequestRepository {
    public void save(LeaveRequest leaveRequest);
    public List<LeaveRequest> findAll();
    public Optional<LeaveRequest> findById(Long id);
}
