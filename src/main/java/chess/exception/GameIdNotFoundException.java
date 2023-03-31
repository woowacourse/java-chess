package chess.exception;

public class GameIdNotFoundException extends CustomException {
    public GameIdNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
