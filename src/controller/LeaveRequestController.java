package controller;

import dto.LeaveRequestDto;
import model.LeaveRequest;
import service.LeaveRequestService;

import java.util.List;

public class LeaveRequestController {
    private LeaveRequestService leaveRequestService;

    public LeaveRequestController(LeaveRequestService leaveRequestService) {
        this.leaveRequestService = leaveRequestService;
    }
    public void submitLeaveRequest(LeaveRequestDto leaveRequestDto) {
        leaveRequestService.submitLeaveRequest(leaveRequestDto);
        System.out.println("Leave Request Submitted for" + leaveRequestDto.getEmployeeName());
    }
    public List<LeaveRequestDto> showAllLeaveRequests() {
        List<LeaveRequestDto> leaveRequestDtos= leaveRequestService.getLeaveRequests();
        for (LeaveRequestDto leaveRequestDto : leaveRequestDtos) {
            System.out.println("employeeName: " + leaveRequestDto.getEmployeeName() +
                    "start:" + leaveRequestDto.getStartDate()+
                    "end:" + leaveRequestDto.getEndDate());
        }
        return leaveRequestDtos;
    }
    public void approveLeaveRequest(LeaveRequestDto leaveRequestDto) {
        leaveRequestService.approveLeaveRequest(leaveRequestDto);
        System.out.println("Leave Request Approved");
    }
}
