package chess.domain.piece;

import chess.domain.Position;
import chess.domain.direction.Direction;
import java.util.List;

public abstract class Piece {

    private final Color color;
    private final String name;
    private final List<Direction> directions;

    protected Piece(Color color, String name, List<Direction> directions) {
        this.color = color;
        this.name = name;
        this.directions = directions;
    }

    public List<Direction> findRoute(Position startPosition, Position targetPosition) {
        return null;
//        return directions.stream()
//                .map(direction -> direction.route(startPosition, targetPosition, isSingleMovable()))
//                .filter(route -> !route.isEmpty())
//                .findAny()
//                .orElseThrow(() -> new IllegalStateException("해당 기물이 갈 수 없는 지점입니다."));
    }

    public final String convertedName() {
        return color.convertToCase(name);
    }

    abstract boolean isSingleMovable();
}
