import controller.EmployeeController;
import controller.LeaveRequestController;
import dto.LeaveRequestDto;
import model.Employee;
import repository.EmployeeRepository;
import repository.EmployeeRepositoryImpl;
import repository.LeaveRequestRepository;
import repository.LeaveRequestRepositoryImpl;
import service.EmployeeService;
import service.LeaveRequestService;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException {
        EmployeeRepositoryImpl employeeRepository = new EmployeeRepositoryImpl();
        LeaveRequestRepository leaveRequestRepository = new LeaveRequestRepositoryImpl(employeeRepository);
        LeaveRequestService leaveRequestService = new LeaveRequestService(leaveRequestRepository);
        LeaveRequestController leaveRequestController = new LeaveRequestController(leaveRequestService);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        EmployeeController employeeController = new EmployeeController(employeeService);
        LeaveRequestDto leaveRequestDto = new LeaveRequestDto();
        Employee employee = new Employee();
        employee.setId(4l);
        employee.setFirstName("nas");
        employee.setLastName("jh");
        employee.setNationalId(568);
        employee.setEmployeeId("mbhj");
     //   employeeController.insertEmployee(employee);
        leaveRequestDto.setStartDate(new Date(2025,10,10));
        leaveRequestDto.setEndDate(new Date(2025, 12, 1));
        leaveRequestDto.setId(5l);
        leaveRequestDto.setApproved(false);
       // leaveRequestController.submitLeaveRequest(employee,leaveRequestDto);
       //   leaveRequestController.showAllLeaveRequests();
        leaveRequestController.approveLeaveRequest(leaveRequestDto);
    }
}