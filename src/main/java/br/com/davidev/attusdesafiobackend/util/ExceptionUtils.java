package br.com.davidev.attusdesafiobackend.util;

import br.com.davidev.attusdesafiobackend.exception.AddressNotAssociatedException;
import br.com.davidev.attusdesafiobackend.exception.ResourceNotFoundException;

public class ExceptionUtils {
    public static ResourceNotFoundException handleResourceNotFoundException() {
        return new ResourceNotFoundException("No records found for this ID!");
    }

    public static AddressNotAssociatedException handleAddressNotAssociatedException(){
        return new AddressNotAssociatedException("The provided address ID is not associated with the specified person ID!");
    }
}
