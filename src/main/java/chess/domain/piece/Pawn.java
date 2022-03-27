package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {

    public static final List<Position> BLACK_INIT_LOCATIONS = List.of(
            Position.of("a7"), Position.of("b7"), Position.of("c7"), Position.of("d7"),
            Position.of("e7"), Position.of("f7"), Position.of("g7"), Position.of("h7"));
    public static final List<Position> WHITE_INIT_LOCATIONS = List.of(
            Position.of("a2"), Position.of("b2"), Position.of("c2"), Position.of("d2"),
            Position.of("e2"), Position.of("f2"), Position.of("g2"), Position.of("h2"));

    private static final Direction BLACK_DIRECTION = Direction.SOUTH;
    private static final Direction WHITE_DIRECTION = Direction.NORTH;

    public Pawn(Color color) {
        super(color, PieceName.PAWN);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        Map<Direction, List<Position>> movable = new HashMap<>();
        if (color == Color.BLACK) {
            movable.put(BLACK_DIRECTION, new ArrayList<>());
            putFirstMovablePositionByDirection(movable, position, BLACK_DIRECTION);
        }
        if (color == Color.WHITE) {
            movable.put(WHITE_DIRECTION, new ArrayList<>());
            putFirstMovablePositionByDirection(movable, position, WHITE_DIRECTION);
        }
        return movable;
    }

    private void putFirstMovablePositionByDirection(Map<Direction, List<Position>> movable, Position position,
                                                    Direction direction) {
        putMovablePositionByDirection(movable, position, direction);
        putMovablePositionByDirection(movable, position.toDirection(direction), direction);
    }

    private void putMovablePositionByDirection(Map<Direction, List<Position>> movable, Position position,
                                               Direction direction) {
        Position nextPosition = position.toDirection(direction);
        if (nextPosition == position) {
            return;
        }
        movable.get(direction).add(nextPosition);
    }

    @Override
    public double getPoint() {
        return 1;
    }
}
