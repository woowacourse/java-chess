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

    public Knight(Color color) {
        super(color, PieceType.KNIGHT);
    }

    @Override
    public Map<Direction, List<Position>> getMovablePositions(Position position) {
        return super.getMovablePositionsByDirections(position, Direction.knightDirections());
    }
}
