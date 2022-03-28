package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public class Knight extends FixedMovablePiece {

    public static final List<Position> BLACK_INIT_LOCATIONS = List.of(
            Position.of("b8"), Position.of("g8"));
    public static final List<Position> WHITE_INIT_LOCATIONS = List.of(
            Position.of("b1"), Position.of("g1"));

    private static final List<Direction> DIRECTIONS = List.of(
            Direction.SSE, Direction.SSW, Direction.NNE, Direction.NNW,
            Direction.EES, Direction.EEN, Direction.WWS, Direction.WWN);
    private static final double KNIGHT_POINT = 2.5;

    public Knight(Color color) {
        super(color, PieceName.KNIGHT);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        return super.getMovablePositionsByDirections(position, DIRECTIONS);
    }

    @Override
    public double getPoint() {
        return KNIGHT_POINT;
    }
}
