package chess.domain.piece.straightmovablepiece;

import chess.domain.piece.Color;
import chess.domain.piece.PieceName;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class Queen extends StraightMovablePiece {

    private final static List<Direction> DIRECTIONS = List.of(
            Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH,
            Direction.NORTH_EAST, Direction.NORTH_WEST, Direction.SOUTH_EAST, Direction.SOUTH_WEST);

    public static Position BLACK_INIT_LOCATION = Position.of("d8");
    public static Position WHITE_INIT_LOCATION = Position.of("d1");

    public Queen(Color color) {
        super(color, PieceName.QUEEN);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        return super.getMovablePositionsByDirections(position, DIRECTIONS);
    }

    @Override
    public double getPoint() {
        return 9;
    }
}
