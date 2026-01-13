package E_Commerce_Spring.exception;

public class ConflictUserResource extends RuntimeException{

    public ConflictUserResource(String msg){
        super(msg);
    }
}
