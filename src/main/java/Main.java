import controller.EmployeeController;
import controller.LeaveRequestController;
import dto.LeaveRequestDto;
import model.Employee;
import repository.*;
import repository.proxy.EmployeeProxyImpl;
import repository.proxy.LeaveRequestProxy;
import service.EmployeeService;
import service.LeaveRequestService;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

import controller.EmployeeController;
import controller.LeaveRequestController;
import dto.LeaveRequestDto;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;
import model.Employee;
import service.EmployeeService;
import service.LeaveRequestService;

import java.sql.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        SeContainerInitializer initializer = SeContainerInitializer.newInstance();
        try (SeContainer container = initializer.initialize()) {

            EmployeeController employeeController = container.select(EmployeeController.class).get();
            LeaveRequestController leaveRequestController = container.select(LeaveRequestController.class).get();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to the hr management Service");

            while (true) {
                Employee employee = new Employee();
                LeaveRequestDto leaveRequestDto = new LeaveRequestDto();

                System.out.println("Please enter your first name:");
                employee.setFirstName(scanner.nextLine());

                System.out.println("Please enter your last name:");
                employee.setLastName(scanner.nextLine());

                System.out.println("Please enter your national code:");
                employee.setNationalId(scanner.nextInt());
                scanner.nextLine();

                System.out.println("Please enter your employee code:");
                employee.setEmployeeId(scanner.nextLine());

                employeeController.insertEmployee(employee);

                System.out.println("Please enter your start date (yyyy-MM-dd):");
                leaveRequestDto.setStartDate(Date.valueOf(scanner.nextLine()));

                System.out.println("Please enter your end date (yyyy-MM-dd):");
                leaveRequestDto.setEndDate(Date.valueOf(scanner.nextLine()));

                leaveRequestController.submitLeaveRequest(leaveRequestDto, employee.getId());
                leaveRequestController.showAllLeaveRequests();
                leaveRequestController.approveLeaveRequest(2L);

                System.out.println("Do you want to continue? (yes/no)");
                if (scanner.nextLine().equalsIgnoreCase("no")) break;
            }
        }
    }
}
