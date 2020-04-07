package chess.domain.move;

import spark.QueryParamsMap;
import spark.Request;

public class MovingInfo {
    private final Position startPosition;
    private final Position targetPosition;
    private static final int REVERSE_BASE = 9;

    private MovingInfo(Position startPosition, Position targetPosition) {
        this.startPosition = startPosition;
        this.targetPosition = targetPosition;
    }

    public static MovingInfo of(Position startPosition, Position targetPosition) {
        return new MovingInfo(startPosition, targetPosition);
    }

    public static MovingInfo reverseMovingInfo(MovingInfo movingInfo) {
        Position startPosition = movingInfo.getStartPosition();
        Position targetPosition = movingInfo.getTargetPosition();
        Coordinate reverseStartX = reverseCoordinate(startPosition.getX());
        Coordinate reverseStartY = reverseCoordinate(startPosition.getY());
        Coordinate reverseTargetX = reverseCoordinate(targetPosition.getX());
        Coordinate reverseTargetY = reverseCoordinate(targetPosition.getY());
        Position ReverseStartPosition = Position.of(reverseStartX, reverseStartY);
        Position ReverseTargetPosition = Position.of(reverseTargetX, reverseTargetY);

        return of(ReverseStartPosition, ReverseTargetPosition);
    }

    private static Coordinate reverseCoordinate(int coordinate) {
        return Coordinate.of(REVERSE_BASE - coordinate);
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }
}
