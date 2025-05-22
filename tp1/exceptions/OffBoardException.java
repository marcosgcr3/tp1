package tp1.exceptions;



public class OffBoardException extends GameModelException {
    
    private static final long serialVersionUID = 1L;
    
    public OffBoardException() {
        super();
    }
    
    public OffBoardException(String message) {
        super(message);
    }
    
    public OffBoardException(Throwable cause) {
        super(cause);
    }
    
    public OffBoardException(String message, Throwable cause) {
        super(message, cause);
    }
}