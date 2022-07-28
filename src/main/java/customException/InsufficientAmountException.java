package customException;

public class InsufficientAmountException extends Exception{
    public InsufficientAmountException(String msg){
        super(msg);
    }
}
