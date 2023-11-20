package shopnet_user.shopnest_user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //404
public class AlreadyExistException extends RuntimeException{
    String message;

   public AlreadyExistException(){

    }
    
    public AlreadyExistException(String message){
        super(message);
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
