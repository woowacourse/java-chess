package chess.exception;

public class InvalidCommandException extends ChessException{
    public InvalidCommandException() {
        super("유효하지 않은 명령입니다.");
    }
}
