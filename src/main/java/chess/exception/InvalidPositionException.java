package chess.exception;

public class InvalidPositionException extends IllegalArgumentException {
    public InvalidPositionException(String position) {
        super(String.format("좌표를 올바른 방식으로 입력해 주세요. 입력 : %s", position));
    }
}
