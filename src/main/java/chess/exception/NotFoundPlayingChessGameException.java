package chess.exception;

public class NotFoundPlayingChessGameException extends RuntimeException {
    private static final String MESSAGE = "진행중인 게임이 없습니다.";

    public NotFoundPlayingChessGameException() {
        super(MESSAGE);
    }

}
