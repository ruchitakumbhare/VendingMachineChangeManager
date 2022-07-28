package customException;

public class InsufficientCoinException extends Exception {
    public InsufficientCoinException(String msg) {
        super(msg);
    }
}
