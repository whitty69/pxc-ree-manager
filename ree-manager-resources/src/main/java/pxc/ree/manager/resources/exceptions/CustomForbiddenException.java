package pxc.ree.manager.resources.exceptions;

/**
 * Created by iowp01 on 20.07.2016.
 */
public class CustomForbiddenException extends AbstractCustomException {

    private static final long serialVersionUID = 998l;

    public CustomForbiddenException() {

    }

    public CustomForbiddenException(String message) {
        super(message);
    }

    public CustomForbiddenException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomForbiddenException(Throwable cause) {
        super(cause);
    }

    public CustomForbiddenException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
