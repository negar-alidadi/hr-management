package controller;

import dto.LeaveRequestDto;
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
    public void submitLeaveRequest(LeaveRequestDto leaveRequestDto,Long employeeId) throws SQLException {
        try {
            leaveRequestService.submitLeaveRequest(leaveRequestDto,employeeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Leave Request Submitted for" + leaveRequestDto.getEmployeeName());
    }

    public List<LeaveRequestDto> showAllLeaveRequests() {
        List<LeaveRequestDto> leaveRequestDtos= null;
        try {
            leaveRequestDtos = leaveRequestService.getLeaveRequests();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (LeaveRequestDto leaveRequestDto : leaveRequestDtos) {
            System.out.println("employeeName: " + leaveRequestDto.getEmployeeName() +
                    "start:" + leaveRequestDto.getStartDate()+
                    "end:" + leaveRequestDto.getEndDate());
        }
        return leaveRequestDtos;
    }
    public void approveLeaveRequest(Long leaveRequestId) {
        try {
            leaveRequestService.approveLeaveRequest(leaveRequestId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Leave Request Approved");
    }
}

