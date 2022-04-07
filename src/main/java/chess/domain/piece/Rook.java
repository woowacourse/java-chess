package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class Rook extends StraightMovablePiece {

    public static final List<Position> BLACK_INIT_LOCATIONS = List.of(
            Position.of("a8"), Position.of("h8"));
    public static final List<Position> WHITE_INIT_LOCATIONS = List.of(
            Position.of("a1"), Position.of("h1"));

    public Rook(Color color) {
        super(color, PieceType.ROOK);
    }

    @Override
    protected List<Direction> getMovableDirections() {
        return Direction.rookDirections();
    }
}
