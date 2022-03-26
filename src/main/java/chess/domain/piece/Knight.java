package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Knight extends Piece {

    public final static List<Direction> DIRECTIONS = List.of(
            Direction.SSE, Direction.SSW, Direction.NNE, Direction.NNW,
            Direction.EES, Direction.EEN, Direction.WWS, Direction.WWN);

    public static List<Position> BLACK_INIT_LOCATIONS = List.of(
            new Position("b8"), new Position("g8"));
    public static List<Position> WHITE_INIT_LOCATIONS = List.of(
            new Position("b1"), new Position("g1"));

    public Knight(Color color) {
        super(color, PieceName.KNIGHT);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        Map<Direction, List<Position>> movable = new HashMap<>();
        for (Direction direction : DIRECTIONS) {
            movable.put(direction, new ArrayList<>());
            putMovablePositionsByDirection(movable, position, direction);
        }
        return movable;
    }

    private void putMovablePositionsByDirection(Map<Direction, List<Position>> movable, Position position,
                                                Direction direction) {
        Position nextPosition = position.toDirection(direction);
        if (nextPosition == position) {
            return;
        }
        movable.get(direction).add(nextPosition);
    }

    @Override
    public double getPoint() {
        return 2.5;
    }
}
