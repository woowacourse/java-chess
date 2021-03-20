package chess.domain.piece;

import java.util.List;
import java.util.Map;

public abstract class AbstractPiece implements Piece {

    protected static final String ERROR_CAN_NOT_MOVE = "기물이 이동할 수 없는 위치입니다.";
    protected final Color color;
    protected final Position position;

    public AbstractPiece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    @Override
    public boolean isSameColor(Color color) {
        return color == this.color;
    }

    @Override
    public boolean isSameColumn(Point point) {
        return position.isSameColumn(point);
    }

    protected Direction findDirection(Position position, List<Direction> directions,
        int ableLength) {
        return directions.stream()
            .filter(direction -> this.position.canMove(position, direction, ableLength))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException(ERROR_CAN_NOT_MOVE));
    }

    protected String changeColorSymbol(String symbol) {
        if (color.isBlack()) {
            return symbol.toUpperCase();
        }
        return symbol;
    }

    protected void validateObstacle(Position position, Direction direction,
        Map<Position, Piece> pieces) {
        for (int distance = 1; distance < 7; distance++) {
            int dx = direction.getXDegree() * distance;
            int dy = direction.getYDegree() * distance;
            Position movePosition = this.position.add(dx, dy);
            if (movePosition.equals(position)) {
                return;
            }
            if (!(pieces.get(movePosition) instanceof Blank)) {
                throw new IllegalArgumentException("이동하는 경로 사이에 기물이 있습니다.");
            }
        }
    }
}
