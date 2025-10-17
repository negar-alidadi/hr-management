package model;
import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "leave_request")
public class LeaveRequest extends AbstractClass {

    @Id
    @SequenceGenerator(name = "request_seq_gen", sequenceName = "request_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_seq_gen")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;

    // keep same logic: employee is referenced by employeeId column
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeId", insertable = true, updatable = true, foreignKey = @ForeignKey(name = "FK_LEAVEREQ_EMP"))
    private Employee employee;

    @Column(name = "startDate")
    private Date startDate;

    @Column(name = "endDate")
    private Date endDate;

    @Column(name = "approved")
    private boolean approved;

    public LeaveRequest() {}

    public LeaveRequest(Long id, Employee employee, Date startDate, Date endDate, boolean approved) {
        this.id = id;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.approved = approved;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public boolean isApproved() { return approved; }
    public void setApproved(boolean approved) { this.approved = approved; }
}
