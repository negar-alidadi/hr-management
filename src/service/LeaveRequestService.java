package service;

import dto.LeaveRequestDto;
import model.Employee;
import model.LeaveRequest;
import repository.LeaveRequestRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LeaveRequestService {
    private LeaveRequestRepository repository;
    public LeaveRequestService(LeaveRequestRepository repository) {
        this.repository = repository;
    }
    public void submitLeaveRequest(Employee employee,LeaveRequestDto leaveRequestDto) throws SQLException {
        leaveRequestDto.setEmployeeId(employee.getId());
        leaveRequestDto.setEmployeeName(employee.getFirstName());
        leaveRequestDto.setEmployeeLastName(employee.getLastName());
        leaveRequestDto.setEmployeeCode(employee.getEmployeeId());
        leaveRequestDto.setNationalId(employee.getNationalId());
        LeaveRequest leaveRequest = new LeaveRequest(leaveRequestDto.getId(),employee,leaveRequestDto.getStartDate(),leaveRequestDto.getEndDate(),false);
        repository.save(leaveRequest);
        System.out.println("done");
    }
    public List<LeaveRequestDto> getLeaveRequests() throws SQLException {
        List<LeaveRequest> leaveRequests = repository.findAll();
        LeaveRequestDto leaveRequestDto = new LeaveRequestDto();
       return leaveRequests.stream().map(r -> {
            leaveRequestDto.setId(r.getId());
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
    public void approveLeaveRequest(LeaveRequestDto leaveRequestDto) throws SQLException {
        LeaveRequest leaveRequest = repository.findById(leaveRequestDto.getEmployeeId());
        leaveRequest.setApproved(true);
        repository.save(leaveRequest);
        System.out.println("Leave Request Approved");
    }
}
