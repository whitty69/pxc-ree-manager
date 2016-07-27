package pxc.ree.manager.resources.exceptions;

/**
 * Created by iowp01 on 20.07.2016.
 */
public class CustomBadRequestException extends AbstractCustomException {

    private static final long serialVersionUID = 999l;


    public CustomBadRequestException(String message) {
        super(message);
    }
}
