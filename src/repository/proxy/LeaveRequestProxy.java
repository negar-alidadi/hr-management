package repository.proxy;

import exception.SqlConnectionEx;
import model.LeaveRequest;
import repository.EmployeeRepository;
import repository.LeaveRequestRepository;
import repository.LeaveRequestRepositoryImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeaveRequestProxy implements LeaveRequestRepository {
    private final LeaveRequestRepositoryImpl realRepo;
    private final List<LeaveRequest> pendingSaveRequests = new ArrayList<>();

    public LeaveRequestProxy(EmployeeRepository employeeRepository) {
        this.realRepo = new LeaveRequestRepositoryImpl(employeeRepository);
    }

    @Override
    public void save(LeaveRequest leaveRequest, Long employeeId) {
        try {
            realRepo.save(leaveRequest, employeeId);
        } catch (SqlConnectionEx e) {
            System.out.println("Database connection failed, saving request to retry list.");
            pendingSaveRequests.add(leaveRequest);
            for (LeaveRequest pendingRequest : pendingSaveRequests) {
                System.out.println("DB insert failed, caching for retry:"+ pendingRequest);
            }
        }
    }

    public void retryPendingSaves() {
        Iterator<LeaveRequest> iterator = pendingSaveRequests.iterator();
        while (iterator.hasNext()) {
            LeaveRequest leaveRequest = iterator.next();
            try {
                realRepo.save(leaveRequest, leaveRequest.getEmployee().getId());
                iterator.remove();
                System.out.println("Retried and saved: " + leaveRequest);
            } catch (SqlConnectionEx e) {
                System.out.println("Still failed: " + leaveRequest);
            }
        }
    }

    public List<LeaveRequest> getPendingSaves() {
        return new ArrayList<>(pendingSaveRequests);
    }

    @Override
    public List<LeaveRequest> findAll() {
      try {
          return realRepo.findAll();
      }catch (SqlConnectionEx e) {
          System.out.println("Database connection failed,can not finding all requests.");
          throw e;
      }
    }

    @Override
    public LeaveRequest findById(Long id) {
       try {
           return realRepo.findById(id);
       }catch (SqlConnectionEx e) {
           System.out.println("Database connection failed" + e.getMessage());
           throw e;
       }
    }

    @Override
    public void update(Long id, LeaveRequest leaveRequest) {
      try {
          realRepo.update(id, leaveRequest);
      }catch (SqlConnectionEx e) {
          System.out.println("Database connection failed" + e.getMessage());
          throw e;
      }
    }

    @Override
    public void delete(Long id) {
      try {
          realRepo.delete(id);
      }catch (SqlConnectionEx e) {
          System.out.println("Database connection failed" + e.getMessage());
          throw e;
      }
    }
}
