package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class King extends FixedMovablePiece {

    public static final Position BLACK_INIT_LOCATION = Position.of("e8");
    public static final Position WHITE_INIT_LOCATION = Position.of("e1");

    public King(Color color) {
        super(color, PieceType.KING);
    }

    @Override
    protected List<Direction> getMovableDirections() {
        return Direction.kingAndQueenDirections();
    }
}
