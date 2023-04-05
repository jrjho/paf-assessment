package ibf2022.paf.assessment.exceptions;

public class Exceptions extends RuntimeException {
    
    public Exceptions() {
        super();
    }
    public Exceptions(String message) {
        super(message);
    }
    public Exceptions(String message, Throwable cause) {
        super(message, cause);
    }
}