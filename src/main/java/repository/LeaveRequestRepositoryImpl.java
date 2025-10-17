package repository;

import exception.SqlConnectionEx;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import model.Employee;
import model.LeaveRequest;


import java.util.List;

@Dependent
public class LeaveRequestRepositoryImpl extends AbstractDao<LeaveRequest> implements LeaveRequestRepository {

    private  EmployeeRepository employeeRepository;

    @Inject
    public LeaveRequestRepositoryImpl(EmployeeRepository employeeRepository) {
        super(LeaveRequest.class);
        this.employeeRepository = employeeRepository;
    }
    public LeaveRequestRepositoryImpl(){
        super(LeaveRequest.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @Transactional
    public void save(LeaveRequest leaveRequest, Long employeeId) {
        try {
            Employee emp = employeeRepository.findEmployeeById(employeeId);
            if (emp == null) throw new SqlConnectionEx("Employee not found with id: " + employeeId);
            leaveRequest.setEmployee(emp);
            persist(leaveRequest);
        } catch (Exception e) {
            throw new SqlConnectionEx("Database connection failed " );
        }
    }

    @Override
    public List<LeaveRequest> findAll() {
        try {
            return super.findAll();
        } catch (Exception e) {
            throw new SqlConnectionEx("Database connection failed ");
        }
    }

    @Override
    public LeaveRequest findById(Long id) {
        try {
            return find(id);
        } catch (Exception e) {
            throw new SqlConnectionEx("Database connection failed " );
        }
    }

    @Override
    @Transactional
    public void update(Long id, LeaveRequest leaveRequest) {
        try {
            LeaveRequest existing = find(id);
            if (existing == null) { System.out.println("No LeaveRequest found with id: " + id); return; }
            if (leaveRequest.getEmployee() != null) {
                Employee emp = employeeRepository.findEmployeeById(leaveRequest.getEmployee().getId());
                existing.setEmployee(emp);
            }
            existing.setStartDate(leaveRequest.getStartDate());
            existing.setEndDate(leaveRequest.getEndDate());
            existing.setApproved(leaveRequest.isApproved());
            merge(existing);
        } catch (Exception e) {
            throw new SqlConnectionEx("Database update failed: " );
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            LeaveRequest existing = find(id);
            if (existing != null) remove(existing);
            else System.out.println("No LeaveRequest found with id: " + id);
        } catch (Exception e) {
            throw new SqlConnectionEx("Database delete failed");
        }
    }
}
