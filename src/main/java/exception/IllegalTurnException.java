package exception;

public class IllegalTurnException extends PieceMoveException {

    private static final String MESSAGE = "[Error] 해당 기물의 턴이 아닙니다.";

    public IllegalTurnException() {
        super(MESSAGE);
    }

}
