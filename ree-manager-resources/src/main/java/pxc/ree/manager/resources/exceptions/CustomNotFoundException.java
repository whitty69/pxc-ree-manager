package pxc.ree.manager.resources.exceptions;

/**
 * Created by iowp01 on 20.07.2016.
 */
public class CustomNotFoundException extends AbstractCustomException {

    private static final long serialVersionUID = 997l;

    public CustomNotFoundException(String message) {
        super(message);
    }
}
