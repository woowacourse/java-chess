package exception;

public class IllegalMoveException extends PieceMoveException {

    private static final String MESSAGE = "[Error] 해당 기물은 target 위치로 이동할 수 없습니다.";

    public IllegalMoveException() {
        super(MESSAGE);
    }

}
