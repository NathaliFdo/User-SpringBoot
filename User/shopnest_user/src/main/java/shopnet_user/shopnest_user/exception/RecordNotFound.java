package shopnet_user.shopnest_user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class RecordNotFound extends RuntimeException{

    String message;

   public RecordNotFound(){

    }
    
    public RecordNotFound(String message){
        super(message);
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
    
}
