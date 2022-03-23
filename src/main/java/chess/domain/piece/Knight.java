package chess.domain.piece;

import static chess.domain.direction.Direction.*;

import chess.domain.Position;
import chess.domain.direction.Direction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Knight extends Piece {

    private static final String KNIGHT_NAME = "N";
    private static final List<Direction> MOVE_DIRECTIONS = Arrays.asList(
            UP_UP_RIGHT, UP_RIGHT_RIGHT, DOWN_DOWN_RIGHT, DOWN_RIGHT_RIGHT,
            UP_UP_LEFT, UP_LEFT_LEFT, DOWN_DOWN_LEFT, DOWN_LEFT_LEFT);

    public Knight(Color color) {
        super(color, KNIGHT_NAME);
    }

    public List<Position> findRoute(Position startPosition, Position targetPosition) {
        Direction direction = MOVE_DIRECTIONS.stream()
                .filter(direct -> direct.isDirection(startPosition, targetPosition))
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
}
