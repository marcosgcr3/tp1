package tp1.exceptions;

public class FileConfigurationException extends GameLoadException {
    
    private static final long serialVersionUID = 1L;
    
    public FileConfigurationException() {
        super();
    }
    
    public FileConfigurationException(String message) {
        super(message);
    }
    
    public FileConfigurationException(Throwable cause) {
        super(cause);
    }
    
    public FileConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}