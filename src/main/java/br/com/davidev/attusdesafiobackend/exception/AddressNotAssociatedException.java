package br.com.davidev.attusdesafiobackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "The provided address ID is not associated with the specified person ID!")
public class AddressNotAssociatedException extends RuntimeException{
    public AddressNotAssociatedException(String message){
        super(message);
    }

}
