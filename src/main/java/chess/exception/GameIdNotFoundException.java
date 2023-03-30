package chess.exception;

public class GameIdNotFoundException extends RuntimeException{
    private static final String ERROR_MESSAGE = "저장을 할 수 없습니다.";

    public GameIdNotFoundException() {
        super(ERROR_MESSAGE);
    }
}
