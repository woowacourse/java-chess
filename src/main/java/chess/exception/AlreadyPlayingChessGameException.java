package chess.exception;

public class AlreadyPlayingChessGameException extends RuntimeException {
    private static final String MESSAGE = "아직 끝나지 않은 게임이 있습니다.";

    public AlreadyPlayingChessGameException() {
        super(MESSAGE);
    }

}
