import controller.LeaveRequestController;
import dto.LeaveRequestDto;
import repository.LeaveRequestRepository;
import repository.LeaveRequestRepositoryImpl;
import service.LeaveRequestService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LeaveRequestRepository leaveRequestRepository =new LeaveRequestRepositoryImpl();
        LeaveRequestService leaveRequestService = new LeaveRequestService(leaveRequestRepository);
        LeaveRequestController leaveRequestController = new LeaveRequestController(leaveRequestService);
        LeaveRequestDto leaveRequestDto = new LeaveRequestDto();
        leaveRequestDto.setEmployeeId(1l);
        leaveRequestDto.setEmployeeName("Albert");
        leaveRequestDto.setEmployeeLastName("Smith");
        leaveRequestDto.setNationalId(456);
        leaveRequestDto.setEmployeeCode("jh4");
        leaveRequestDto.setStartDate(LocalDate.of(2020, 1, 1));
        leaveRequestDto.setEndDate(LocalDate.of(2020, 12, 1));
        leaveRequestDto.setApproved(false);
        leaveRequestController.submitLeaveRequest(leaveRequestDto);
        leaveRequestController.showAllLeaveRequests();
       // leaveRequestController.approveLeaveRequest(leaveRequestDto);
    }
}