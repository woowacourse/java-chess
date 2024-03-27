package chess.domain.piece.exception;

public class ObstacleException extends IllegalStateException {

    private static final String message = "기물로 막혀있어 이동할 수 없습니다.";

    public ObstacleException() {
        super(message);
    }
}
