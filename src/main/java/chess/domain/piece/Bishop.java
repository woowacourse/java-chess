package chess.domain.piece;

import chess.domain.color.Color;
import java.util.Set;

public class Bishop extends SlidingPiece {
    private static Set<Direction> DIRECTIONS = Direction.getDiagonalDirection();

    public Bishop(Position position, Color color) {
        super(position, color, DIRECTIONS);
    }

    @Override
    public PieceType pieceType() {
        if (color == Color.WHITE) {
            return PieceType.WHITE_BISHOP;
        }
        return PieceType.BLACK_BISHOP;
    }

    @Override
    public Bishop update(Position destination) {
        return new Bishop(destination, color);
    }
}
