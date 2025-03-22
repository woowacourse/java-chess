package chess.exception;

public class InvalidMoveException extends IllegalArgumentException{
    public InvalidMoveException(){
        super("유효하지 않은 기물 움직임입니다");
    }
}
