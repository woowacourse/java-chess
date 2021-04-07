package exception;

public class SourcePositionException extends PieceMoveException {

    private static final String MESSAGE = "[Error] source 위치에 기물이 없습니다.";

    public SourcePositionException() {
        super(MESSAGE);
    }

}
