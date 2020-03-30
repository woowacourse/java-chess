package chess.exceptions;

public class InvalidInputException extends IllegalArgumentException {
    public InvalidInputException() {
        super("잘못된 입력을 하셨습니다.");
    }
}
