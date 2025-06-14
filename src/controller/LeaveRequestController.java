package controller;

import dto.LeaveRequestDto;
import exception.SqlConnectionEx;
import model.Employee;
import model.LeaveRequest;
import service.LeaveRequestService;

import java.sql.SQLException;
import java.util.List;

public class LeaveRequestController {
    private LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }
    public void submitLeaveRequest(LeaveRequestDto leaveRequestDto,Long employeeId) {
        try {
            leaveRequestService.submitLeaveRequest(leaveRequestDto,employeeId);
        } catch (SqlConnectionEx e) {

        }
        System.out.println("Leave Request Submitted for " + leaveRequestDto.getEmployeeName());
    }

    public List<LeaveRequestDto> showAllLeaveRequests()   {
        List<LeaveRequestDto> leaveRequestDtos= null;
        try {
            leaveRequestDtos = leaveRequestService.getLeaveRequests();
        for (LeaveRequestDto leaveRequestDto : leaveRequestDtos) {
            System.out.println("employeeName: " + leaveRequestDto.getEmployeeName() +
                    "start: " + leaveRequestDto.getStartDate()+
                    "end: " + leaveRequestDto.getEndDate());
        }
        } catch (SqlConnectionEx e) {
            System.out.println("Database connection failed,can not finding all requests." + e.getMessage());
        }
        return leaveRequestDtos;
    }
    public void approveLeaveRequest(Long leaveRequestId)   {
        try {
            leaveRequestService.approveLeaveRequest(leaveRequestId);
        System.out.println("Leave Request Approved for id: " + leaveRequestId);
        } catch (SqlConnectionEx e) {
            System.out.println("Database connection failed,can not approve leave request."+e.getMessage());
        }
    }
}

