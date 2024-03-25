package chess.domain.piece.sliding;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import java.util.Set;

public class Bishop extends SlidingPiece {
    private static final Set<Direction> DIRECTIONS = Direction.getDiagonalDirection();

    public Bishop(Color color) {
        super(color, DIRECTIONS);
    }

    @Override
    public PieceType pieceType() {
        if (color == Color.WHITE) {
            return PieceType.WHITE_BISHOP;
        }
        return PieceType.BLACK_BISHOP;
    }
}
