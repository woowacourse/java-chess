package chess.domain.exceptions;

public class TerminateException extends RuntimeException {

    public TerminateException(String exceptionMessage) {
        super(exceptionMessage);
    }
}
