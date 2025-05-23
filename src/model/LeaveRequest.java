package model;


import java.sql.Date;

public class LeaveRequest {
   private Long id;
    private Employee employee;
    private Date startDate;
    private Date endDate;
    private boolean approved = false;


    public LeaveRequest() {
    }

    public LeaveRequest(Long id,Employee employee, Date startDate, Date endDate, boolean approved) {
        this.id = id;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approved = approved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    @Override
    public String toString() {
        return "EmployeeRequest{" +
               // "id=" + id +
                ", employee=" + employee +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", approved=" + approved +
                '}';
    }

}
