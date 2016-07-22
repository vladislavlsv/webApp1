package myApp.Exceptions;

public class DaoSysException extends DaoException {

    public DaoSysException(String message) {
        super(message);
    }

    public DaoSysException(String message, Throwable cause) {
        super(message, cause);
    }
}
