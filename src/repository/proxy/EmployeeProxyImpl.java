package repository.proxy;

import exception.SqlConnectionEx;
import model.Employee;
import repository.EmployeeRepository;
import repository.EmployeeRepositoryImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeProxyImpl implements EmployeeRepository {
    private final EmployeeRepositoryImpl realDao = new EmployeeRepositoryImpl();
    private final List<Employee> failedInserts = new ArrayList<>();

    @Override
    public Employee save(Employee employee) {
        try {
           return realDao.save(employee);
        } catch (SqlConnectionEx e) {
            System.out.println("DB insert failed, caching for retry: " + employee);
            failedInserts.add(employee);
            return employee;
        }
    }

    @Override
    public Employee findEmployeeById(Long id) {
        try {
            return realDao.findEmployeeById(id);
        } catch (SqlConnectionEx e) {
            System.out.println("findEmployeeById failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void update(Long id, Employee employee) {
        try {
            realDao.update(id,employee);
        } catch (SqlConnectionEx e) {
            System.out.println("updateEmployee failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        try {
            realDao.delete(id);
        } catch (SqlConnectionEx e) {
            System.out.println("deleteEmployee failed: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public List<Employee> findAll() {
        try {
            return realDao.findAll();
        } catch (SqlConnectionEx e) {
            System.out.println("findAllEmployees failed: " + e.getMessage());
            throw e;
        }
    }

    public void retryFailedInserts() {
        Iterator<Employee> iterator = failedInserts.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            try {
                realDao.save(employee);
                iterator.remove();
                System.out.println("Retried insert succeeded: " + employee);
            } catch (SqlConnectionEx e) {
                System.out.println("Retry still failed: " + employee);
            }
        }
    }

    public List<Employee> getFailedInserts() {
        return failedInserts;
    }
}

