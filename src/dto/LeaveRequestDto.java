package dto;

import java.sql.Date;
import java.time.LocalDate;

public class LeaveRequestDto {
    private Long id;
    private Long employeeId;
    private String employeeFirstName;
    private String employeeLastName;
    private int nationalId;
    private String employeeCode;
    private Date startDate;
    private Date endDate;
    private boolean approved = false;

    public LeaveRequestDto(Long employeeId, String employeeFirstName, Date endDate, Date startDate, String employeeCode, int nationalId, String employeeLastName, boolean approved) {
        this.employeeId = employeeId;
        this.employeeFirstName = employeeFirstName;
        this.endDate = endDate;
        this.startDate = startDate;
        this.employeeCode = employeeCode;
        this.nationalId = nationalId;
        this.employeeLastName = employeeLastName;
        this.approved = approved;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
