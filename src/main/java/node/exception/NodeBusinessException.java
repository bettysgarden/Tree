package node.exception;

public class NodeBusinessException extends Exception
{
    public NodeBusinessException() {
    }

    public NodeBusinessException(String message) {
        super(message);
    }

    public NodeBusinessException(Throwable cause) {
        super(cause);
    }

    public NodeBusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
