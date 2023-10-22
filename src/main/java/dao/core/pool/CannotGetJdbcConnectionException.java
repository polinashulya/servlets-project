package dao.core.pool;

public class CannotGetJdbcConnectionException extends RuntimeException{

    public CannotGetJdbcConnectionException(Throwable cause) {
        super(cause);
    }
}
