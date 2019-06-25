package chess.exception;

public class InvalidTurnException extends IllegalArgumentException{
    public InvalidTurnException() {
        super("선택하신 체스 Piece는 현재 차례가 아닙니다.");
    }

    public InvalidTurnException(String s) {
        super(s);
    }
}
