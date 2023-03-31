package chess.model.exception;

public class ObstacleExistException extends ChessException {

    public ObstacleExistException() {
        super(ChessExceptionType.OBSTACLE_EXIST);
    }
}
