package chess.domain.piece;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
            if (!this.position.isAdd(dx, dy)) {
                return;
            }
            Position movePosition = this.position.add(dx, dy);
            if (movePosition.equals(position)) {
                return;
            }
            if (!(pieces.get(movePosition) instanceof Blank)) {
                throw new IllegalArgumentException("이동하는 경로 사이에 기물이 있습니다.");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractPiece that = (AbstractPiece) o;
        return color == that.color && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, position);
    }
}
