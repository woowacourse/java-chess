package chess.model.exception;

public abstract class ChessException extends RuntimeException {

    private final ChessExceptionType type;

    public ChessException(final ChessExceptionType type) {
        this.type = type;
    }

    public final ChessExceptionType getType() {
        return type;
    }
}
