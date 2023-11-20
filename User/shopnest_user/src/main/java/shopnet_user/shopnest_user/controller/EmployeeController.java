package shopnet_user.shopnest_user.controller;

import java.util.List; // Import the correct List class
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody; // Import this for response serialization
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import shopnet_user.shopnest_user.exception.AlreadyExistException;
import shopnet_user.shopnest_user.exception.ErrorResponse;
import shopnet_user.shopnest_user.exception.RecordNotFound;
import shopnet_user.shopnest_user.model.Employee;
import shopnet_user.shopnest_user.repository.EmployeeRepo;

@RestController
@RequestMapping(value = "/emp")
public class EmployeeController {

    @Autowired
    EmployeeRepo employeeRepo;

    // http://localhost:port/emp/add
    @PostMapping("/add")
    public @ResponseBody Employee addEmployee(@RequestBody Employee employee) throws AlreadyExistException{
        // if(employeeRepo.findById(employee.getId()).isPresent()){
        //     throw new AlreadyExistException("Employee with id" +employee.getId()+"is already present");
        // }
        
        if(employeeRepo.findByName(employee.getName())!=null){
            throw new AlreadyExistException("Employee with name " +employee.getName()+" is already present");
        }
        employeeRepo.save(employee);
        return employee;
    }

    //already exist
    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT) //404
    ResponseEntity<?> handleAlreadyExistException(AlreadyExistException exception){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT,"Already exist exception", exception.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }

    //record not found handler method
    @ExceptionHandler(RecordNotFound.class)
    @ResponseStatus(HttpStatus.CONFLICT) //404
    ResponseEntity<?> handleRecordNotFoundException(RecordNotFound exception){
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.CONFLICT,"Record not found exception", exception.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }


    @GetMapping("/getAll")
    public @ResponseBody List<Employee> getEmployee() {
        List<Employee> employees = employeeRepo.findAll();
        return employees;
    }

    @PutMapping("/update/{id}")
    ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee) throws Exception{
        if(employeeRepo.findById(id).isPresent()){
            Employee employee = employeeRepo.findById(id).get();
            // if(employee == null){
            //     throw new Exception("Employee not found");
            // }
            employee.setName(updatedEmployee.getName());
            Employee updatedEmp = employeeRepo.save(employee);
            return ResponseEntity.ok(updatedEmp);
        }
        else{
            throw new RecordNotFound("Employee with id " +id+ " Not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteEmployee(@PathVariable Integer id) throws Exception{
        Employee employee = employeeRepo.findById(id).get();
        if(employee == null){
            throw new Exception("Employee not found");
        }

        employeeRepo.deleteById(id);
        return ResponseEntity.ok("Employee record deleted successfully.");
    }

    @PatchMapping("/patch/{id}")
    ResponseEntity<Employee> patchEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployee) throws Exception{
        Employee employee = employeeRepo.findById(id).get();
        if(employee == null){
            throw new Exception("Employee not found");
        }

        employee.setName(updatedEmployee.getName());

        Employee updatedEmp = employeeRepo.save(employee);

        return ResponseEntity.ok(updatedEmp);
    }

}
