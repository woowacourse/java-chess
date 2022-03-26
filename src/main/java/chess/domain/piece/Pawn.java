package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {

    public static Direction BLACK_DIRECTION = Direction.SOUTH;
    public static Direction WHITE_DIRECTION = Direction.NORTH;

    public static List<Position> BLACK_INIT_LOCATIONS = List.of(
            new Position("a7"), new Position("b7"), new Position("c7"), new Position("d7"),
            new Position("e7"), new Position("f7"), new Position("g7"), new Position("h7"));
    public static List<Position> WHITE_INIT_LOCATIONS = List.of(
            new Position("a2"), new Position("b2"), new Position("c2"), new Position("d2"),
            new Position("e2"), new Position("f2"), new Position("g2"), new Position("h2"));

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
