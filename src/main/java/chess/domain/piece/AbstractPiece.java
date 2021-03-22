package chess.domain.piece;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public abstract class AbstractPiece implements Piece {

    private static final int MIN_DISTANCE = 1;
    private static final int MAX_DISTANCE = 8;
    protected static final String ERROR_CAN_NOT_MOVE = "기물이 이동할 수 없는 위치입니다.";
    protected final Color color;
    protected final Position position;

    public AbstractPiece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    @Override
    public boolean isSameColor(final Color color) {
        return color == this.color;
    }

    @Override
    public boolean isSameColor(final Piece piece) {
        return piece.isSameColor(color);
    }

    @Override
    public boolean isSameColumn(final Point point) {
        return position.isSameColumn(point);
    }

    protected Direction findDirection(final Position position, final List<Direction> directions,
        final int ableLength) {
        return directions.stream()
            .filter(direction -> this.position.canMove(position, direction, ableLength))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(ERROR_CAN_NOT_MOVE));
    }

    protected String changeColorSymbol(final String symbol) {
        if (color.isBlack()) {
            return symbol.toUpperCase();
        }
        return symbol;
    }

    protected void validateObstacle(final Position position, final Direction direction,
        Map<Position, Piece> pieces) {
        int dx = direction.getXDegree();
        int dy = direction.getYDegree();

        boolean isObstacle = IntStream.range(MIN_DISTANCE, findDistance(position, direction))
            .mapToObj(distance -> this.position.add(dx * distance, dy * distance))
            .anyMatch(movePosition -> !(pieces.get(movePosition) instanceof Blank));

        if (isObstacle) {
            throw new IllegalArgumentException("이동하는 경로 사이에 기물이 있습니다.");
        }
    }

    private int findDistance(final Position position, final Direction direction) {
        int dx = direction.getXDegree();
        int dy = direction.getYDegree();

        int distance = 0;
        boolean isStop = false;
        while (!isStop && distance++ < MAX_DISTANCE) {
            Position movePosition = this.position.add(dx * distance, dy * distance);
            isStop = position.equals(movePosition);
        }
        return distance;
    }
}
