package chess.domain.piece.straightmovablepiece;

import chess.domain.piece.Color;
import chess.domain.piece.PieceName;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public final class Rook extends StraightMovablePiece {

    private static final List<Direction> DIRECTIONS = List.of(
            Direction.EAST, Direction.WEST, Direction.SOUTH, Direction.NORTH);

    public static final List<Position> BLACK_INIT_LOCATIONS = List.of(Position.of("a8"), Position.of("h8"));
    public static final List<Position> WHITE_INIT_LOCATIONS = List.of(Position.of("a1"), Position.of("h1"));

    private static final int ROOK_POINT = 5;

    public Rook(Color color) {
        super(color, PieceName.ROOK);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        return super.getMovablePositionsByDirections(position, DIRECTIONS);
    }

    @Override
    public double getPoint() {
        return ROOK_POINT;
    }
}
