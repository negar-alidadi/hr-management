package repository;

import dto.LeaveRequestDto;
import model.LeaveRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LeaveRequestRepositoryImpl implements LeaveRequestRepository {
    private List<LeaveRequest> leaveRequests = new ArrayList<>();

    @Override
    public void save(LeaveRequest leaveRequest) {

        leaveRequests.add(leaveRequest);

    }

    @Override
    public List<LeaveRequest> findAll() {
        return leaveRequests;
    }

    @Override
    public Optional<LeaveRequest> findById(Long id) {
        return leaveRequests.stream()
                .filter(leaveRequest -> leaveRequest.getEmployee().getEmployeeId().equals(id))
                .findFirst();
    }
}
