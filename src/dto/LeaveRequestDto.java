package dto;

import java.time.LocalDate;

public class LeaveRequestDto {
    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private int nationalId;
    private String employeeCode;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean approved = false;

    public LeaveRequestDto(Long employeeId, String employeeFirstName, LocalDate endDate, LocalDate startDate, String employeeCode, int nationalId, String employeeLastName, boolean approved) {
        this.employeeId = employeeId;
        this.employeeFirstName = employeeFirstName;
        this.endDate = endDate;
        this.startDate = startDate;
        this.employeeCode = employeeCode;
        this.nationalId = nationalId;
        this.employeeLastName = employeeLastName;
        this.approved = approved;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public LeaveRequestDto() {
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeFirstName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeFirstName = employeeName;
    }

    public int getNationalId() {
        return nationalId;
    }

    public void setNationalId(int nationalId) {
        this.nationalId = nationalId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
