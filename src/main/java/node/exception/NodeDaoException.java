package node.exception;

public class NodeDaoException extends Exception
{
    public NodeDaoException() {
    }

    public NodeDaoException(String message) {
        super(message);
    }

    public NodeDaoException(Throwable cause) {
        super(cause);
    }

    public NodeDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
