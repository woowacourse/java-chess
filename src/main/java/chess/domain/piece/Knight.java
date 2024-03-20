package chess.domain.piece;

import chess.domain.color.Color;
import java.util.Set;

public class Knight extends NonSlidingPiece {
    private static Set<Direction> DIRECTIONS = Direction.getKnightDirection();

    public Knight(Position position, Color color) {
        super(position, color, DIRECTIONS);
    }

    @Override
    public Knight update(Position destination) {
        return new Knight(destination, color);
    }

    @Override
    public PieceType pieceType() {
        if (color == Color.WHITE) {
            return PieceType.WHITE_KNIGHT;
        }
        return PieceType.BLACK_KNIGHT;
    }
}
