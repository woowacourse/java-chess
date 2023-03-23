package chess.domain.piece;

import chess.domain.Color;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;

public class Empty extends Piece {

    private static final List<Direction> MOVABLE_DIRECTIONS = List.of();

    private Empty(final Color color, final PieceType pieceType, List<Direction> movableDirection) {
        super(color, pieceType, movableDirection);
    }

    public static Empty create() {
        return new Empty(Color.NONE, PieceType.EMPTY, MOVABLE_DIRECTIONS);
    }

    @Override
    public void canMove(final Position start, final Position end) {
        throw new IllegalStateException("빈 피스는 움직일 수 없습니다.");
    }
}
