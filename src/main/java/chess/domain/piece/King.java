package chess.domain.piece;

import chess.domain.color.Color;
import java.util.Set;

public class King extends NonSlidingPiece {
    private static Set<Direction> DIRECTIONS = Direction.getEightDirection();

    public King(Position position, Color color) {
        super(position, color, DIRECTIONS);
    }

    @Override
    public King update(Position destination) {
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
