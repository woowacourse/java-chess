package chess.domain.piece;

import chess.domain.Position;
import chess.domain.direction.Direction;
import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    private final Color color;
    private final String name;
    private final List<Direction> directions;

    protected Piece(Color color, String name, final List<Direction> directions) {
        this.color = color;
        this.name = name;
        this.directions = directions;
    }

    public List<Position> findRoute(Position startPosition, Position targetPosition) {
        Direction direction = directions.stream()
                .filter(direct -> direct.isDirection(startPosition, targetPosition, isSingleMove()))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("해당 기물이 갈 수 없는 지점입니다."));

        List<Position> positions = new ArrayList<>();
        Position position = startPosition;
        while (!position.equals(targetPosition)) {
            position = direction.move(position);
            positions.add(position);
        }
        return positions;
    }

    public final String convertedName() {
        return color.convertToCase(name);
    }

    abstract boolean isSingleMove();
}
