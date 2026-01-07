package ProjetoNelio.exception;

public class ConflictUserResource extends RuntimeException{

    public ConflictUserResource(String msg){
        super(msg);
    }
}
