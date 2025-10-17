package repository;

import exception.SqlConnectionEx;
import java.util.List;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import model.Employee;


@Dependent
public class EmployeeRepositoryImpl extends AbstractDao<Employee> implements EmployeeRepository {

//    @PersistenceContext(unitName = "hr")
//    private EntityManager em;

    public EmployeeRepositoryImpl() {
        super(Employee.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        try {
            persist(employee);
            return employee;
        } catch (Exception e) {
            throw new SqlConnectionEx("database persist failed " );
        }
    }

    @Override
    public List<Employee> findAll() {
        try {
            return super.findAll();
        }  catch (Exception e) {
            throw new SqlConnectionEx("database query failed " );
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        try {
            Employee e = find(id);
            if (e != null) remove(e);
            else System.out.println("No employee found with id: " + id);
        } catch (Exception ex) {
            throw new SqlConnectionEx("database delete failed ");
        }
    }

    @Override
    @Transactional
    public void update(Long id, Employee employee) {
        try {
            Employee existing = find(id);
            if (existing == null) {
                System.out.println("No employee found with id: " + id);
                return;
            }
            existing.setFirstName(employee.getFirstName());
            existing.setLastName(employee.getLastName());
            existing.setNationalId(employee.getNationalId());
            existing.setEmployeeId(employee.getEmployeeId());
            merge(existing);
        } catch (Exception ex) {
            throw new SqlConnectionEx("database update failed " );
        }
    }

    @Override
    public Employee findEmployeeById(Long id) {
        try {
            return find(id);
        } catch (Exception e) {
            throw new SqlConnectionEx("database query failed " );
        }
    }
}

