package chess.domain.exceptions;

public class OverDistanceException extends RuntimeException {

    public OverDistanceException( ) {
        super("해당 말의 이동 가능한 거리를 초과했습니다.");
    }
}
