package ProjetoNelio.exception;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Object id){
        super("Resurce not found. Id: "+id);
    }
}
