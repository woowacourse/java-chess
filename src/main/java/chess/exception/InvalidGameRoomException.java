package chess.exception;

public class InvalidGameRoomException extends RuntimeException {
    public InvalidGameRoomException(String message) {
        super(message);
    }
}
