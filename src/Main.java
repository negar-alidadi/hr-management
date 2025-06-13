import controller.EmployeeController;
import controller.LeaveRequestController;
import dto.LeaveRequestDto;
import model.Employee;
import repository.*;
import service.EmployeeService;
import service.LeaveRequestService;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        EmployeeRepository employeeRepository = new EmployeeProxyImpl();
        LeaveRequestRepository leaveRequestRepository = new LeaveRequestRepositoryImpl(employeeRepository);

        LeaveRequestService leaveRequestService = new LeaveRequestService(leaveRequestRepository,employeeRepository);
        EmployeeService employeeService = new EmployeeService(employeeRepository);

        LeaveRequestController leaveRequestController = new LeaveRequestController(leaveRequestService);
        EmployeeController employeeController = new EmployeeController(employeeService);

        LeaveRequestDto leaveRequestDto = new LeaveRequestDto();
        Employee employee = new Employee();
        Scanner scanner = new Scanner(System.in);
        System.out.println("welcome to the Leave Request Service");
        while (true) {
        System.out.println("please enter your first name");
        employee.setFirstName(scanner.nextLine());
        System.out.println("please enter your last name");
        employee.setLastName(scanner.nextLine());
        System.out.println("please enter your national code");
        employee.setNationalId(scanner.nextInt());
        System.out.println("please enter your employee code");
        scanner.next();
        String employeeCode = scanner.nextLine();
        employee.setEmployeeId(employeeCode);
        employeeController.insertEmployee(employee);
        System.out.println("please enter your start date (yyyy-MM-dd):");
        String startDateStr = scanner.nextLine();
        leaveRequestDto.setStartDate(Date.valueOf(startDateStr));
        System.out.println("please enter your end date (yyyy-MM-dd):");
        String endDateStr = scanner.nextLine();
        leaveRequestDto.setEndDate(Date.valueOf(endDateStr));
       leaveRequestController.submitLeaveRequest(leaveRequestDto, employee.getId());
        leaveRequestController.showAllLeaveRequests();
        leaveRequestController.approveLeaveRequest(8l);
            System.out.println("do u want to continue?");
            if (scanner.nextLine().equalsIgnoreCase("no")) break;
      //  employeeController.getAllEmployees().forEach(System.out::println);
        }
    }
}