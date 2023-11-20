package shopnet_user.shopnest_user.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import shopnet_user.shopnest_user.model.Employee;
import java.util.List;


public interface EmployeeRepo extends JpaRepository<Employee, Integer>{
    Employee findByName(String name);
}
