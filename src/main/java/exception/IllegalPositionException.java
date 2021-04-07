package exception;

public class IllegalPositionException extends PieceMoveException {

    private static final String MESSAGE = "[Error] 유효하지 않은 체스 좌표 입니다.";

    public IllegalPositionException() {
        super(MESSAGE);
    }

}
