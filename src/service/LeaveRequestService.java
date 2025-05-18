package service;

import dto.LeaveRequestDto;
import model.Employee;
import model.LeaveRequest;
import repository.LeaveRequestRepository;

import java.util.List;
import java.util.Optional;

public class LeaveRequestService {
    private LeaveRequestRepository repository;
    public LeaveRequestService(LeaveRequestRepository repository) {
        this.repository = repository;
    }
    public void submitLeaveRequest(LeaveRequestDto leaveRequestDto) {
        Employee employee = new Employee(leaveRequestDto.getEmployeeId(),
                leaveRequestDto.getEmployeeName(),
                leaveRequestDto.getEmployeeLastName(),
                leaveRequestDto.getNationalId(),
                leaveRequestDto.getEmployeeCode());
        LeaveRequest leaveRequest = new LeaveRequest(employee,leaveRequestDto.getStartDate(),leaveRequestDto.getEndDate(),false);
        repository.save(leaveRequest);
    }
    public List<LeaveRequestDto> getLeaveRequests() {
        List<LeaveRequest> leaveRequests = repository.findAll();
       return leaveRequests.stream().map(r -> {
            LeaveRequestDto leaveRequestDto = new LeaveRequestDto();
            leaveRequestDto.setEmployeeId(r.getEmployee().getId());
            leaveRequestDto.setEmployeeName(r.getEmployee().getFirstName());
            leaveRequestDto.setEmployeeLastName(r.getEmployee().getLastName());
            leaveRequestDto.setNationalId(r.getEmployee().getNationalId());
            leaveRequestDto.setStartDate(r.getStartDate());
            leaveRequestDto.setEndDate(r.getEndDate());
            leaveRequestDto.setApproved(r.isApproved());
            return leaveRequestDto;
        }).toList();
    }
    public void approveLeaveRequest(LeaveRequestDto leaveRequestDto) {
        LeaveRequest leaveRequest = repository.findById(leaveRequestDto.getEmployeeId())
                .orElseThrow(()->new RuntimeException("Leave Request with id" +leaveRequestDto.getEmployeeId()+  "Not Found"));
        leaveRequest.setApproved(true);
        repository.save(leaveRequest);
        System.out.println("Leave Request Approved");
    }
}
