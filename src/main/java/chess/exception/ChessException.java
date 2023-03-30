package chess.exception;

public class ChessException extends RuntimeException {

    private final ExceptionCode code;

    public ChessException(final ExceptionCode code) {
        super(code.name());
        this.code = code;
    }

    public ExceptionCode getCode() {
        return code;
    }
}
