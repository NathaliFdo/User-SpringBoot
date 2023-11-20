package shopnet_user.shopnest_user.exception;

import org.springframework.http.HttpStatus;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ErrorResponse {
    private HttpStatus statusCode;
    private String message;
    private String description;

    public ErrorResponse(HttpStatus conflict, String message, String description){
        super();
        this.statusCode = conflict;
        this.message = message;
        this.description=description;
    }

    public HttpStatus getHttpStatus(){
        return statusCode;
    }

    public void setCode(HttpStatus errorCode){
        this.statusCode = errorCode;
    }

    public String getMessage(){
        return message;
    }

    public void setMsg(String msg){
        this.message=msg;
    }

     public String getDesc(){
        return description;
    }

    public void setDesc(String desc){
        this.description=desc;
    }



}
