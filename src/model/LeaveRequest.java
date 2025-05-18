package model;

import java.time.LocalDate;

public class LeaveRequest {
  //  private Long id;
    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean approved = false;


    public LeaveRequest() {
    }

    public LeaveRequest(Employee employee, LocalDate startDate, LocalDate endDate, boolean approved) {
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approved = approved;
    }

   // public Long getId() {
  //      return id;
 //   }

  //  public void setId(Long id) {
   //     this.id = id;
  //  }
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
