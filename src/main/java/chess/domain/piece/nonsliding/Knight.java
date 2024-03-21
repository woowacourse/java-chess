package chess.domain.piece.nonsliding;

import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import java.util.Set;

public class Knight extends NonSlidingPiece {
    private static final Set<Direction> DIRECTIONS = Direction.getKnightDirection();

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
