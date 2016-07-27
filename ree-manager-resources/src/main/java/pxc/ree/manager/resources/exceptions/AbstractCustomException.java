package pxc.ree.manager.resources.exceptions;

/**
 * Created by iowp01 on 20.07.2016.
 */
public abstract class AbstractCustomException extends RuntimeException {


    public AbstractCustomException() {
        super();
    }

    public AbstractCustomException(String message) {
        super(message);
    }

    public AbstractCustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractCustomException(Throwable cause) {
        super(cause);
    }

    protected AbstractCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
