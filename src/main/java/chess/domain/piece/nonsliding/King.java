package chess.domain.piece.nonsliding;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import java.util.Set;

public class King extends NonSlidingPiece {
    private static final Set<Direction> DIRECTIONS = Direction.getEightDirection();

    public King(final Position position, final Color color) {
        super(position, color, DIRECTIONS);
    }

    @Override
    public King update(final Position destination) {
        return new King(destination, color);
    }

    @Override
    public PieceType pieceType() {
        if (color == Color.WHITE) {
            return PieceType.WHITE_KING;
        }
        return PieceType.BLACK_KING;
    }
}
