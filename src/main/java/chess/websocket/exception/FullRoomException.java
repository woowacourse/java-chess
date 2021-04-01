package chess.websocket.exception;

public class FullRoomException extends RuntimeException {

    private static final String MESSAGE = "룸이 가득 차있습니다.";

    public FullRoomException() {
        super(MESSAGE);
    }
}
