package model;

import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class Employee extends AbstractClass {

    @Id
    @SequenceGenerator(name = "employee_seq_gen", sequenceName = "employee_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq_gen")
    @Column(name = "ID", unique = true, nullable = false, insertable = true, updatable = false)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "nationalId")
    private int nationalId;

    @Column(name = "employeeId")
    private String employeeId;

    public Employee() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public int getNationalId() { return nationalId; }
    public void setNationalId(int nationalId) { this.nationalId = nationalId; }

    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
}
