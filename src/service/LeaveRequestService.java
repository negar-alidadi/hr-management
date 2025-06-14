package service;

import dto.LeaveRequestDto;
import exception.SqlConnectionEx;
import model.Employee;
import model.LeaveRequest;
import repository.EmployeeRepository;
import repository.LeaveRequestRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LeaveRequestService {
    private LeaveRequestRepository repository;
    private EmployeeRepository employeeRepository;
    public LeaveRequestService(LeaveRequestRepository repository, EmployeeRepository employeeRepository) {
        this.repository = repository;
        this.employeeRepository = employeeRepository;
    }
    public void submitLeaveRequest(LeaveRequestDto leaveRequestDto, Long employeeId) throws SqlConnectionEx {
      try {
          Employee employee = employeeRepository.findEmployeeById(employeeId);
        leaveRequestDto.setEmployeeId(employee.getId());
        leaveRequestDto.setEmployeeName(employee.getFirstName());
        leaveRequestDto.setEmployeeLastName(employee.getLastName());
        leaveRequestDto.setEmployeeCode(employee.getEmployeeId());
        leaveRequestDto.setNationalId(employee.getNationalId());
        LeaveRequest leaveRequest = new LeaveRequest(leaveRequestDto.getId(),employee,leaveRequestDto.getStartDate(),leaveRequestDto.getEndDate(),false);
        repository.save(leaveRequest, employeeId);
      }catch (SqlConnectionEx e) {
              System.out.println("findEmployeeById failed: " + e.getMessage());
          }
    }
    public List<LeaveRequestDto> getLeaveRequests() throws SqlConnectionEx {
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
    public void approveLeaveRequest(Long leaveRequestId) throws SqlConnectionEx {
        LeaveRequest leaveRequest = repository.findById(leaveRequestId);
        leaveRequest.setApproved(true);
        repository.update(leaveRequestId ,leaveRequest);
        System.out.println("Leave Request Approved");
    }
}
