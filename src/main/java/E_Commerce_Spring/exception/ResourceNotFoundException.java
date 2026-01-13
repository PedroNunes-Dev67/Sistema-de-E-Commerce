package E_Commerce_Spring.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Object id){
        super("Resurce not found. Id: "+id);
    }
}
