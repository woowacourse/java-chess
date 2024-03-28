package chess.domain.piece.nonsliding;

import chess.domain.score.Score;
import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import java.util.Set;

public final class Knight extends NonSlidingPiece {
    private static final Set<Direction> DIRECTIONS = Direction.getKnightDirection();

    public Knight(Color color) {
        super(color, DIRECTIONS);
    }

    @Override
    public PieceType pieceType() {
        if (color == Color.WHITE) {
            return PieceType.WHITE_KNIGHT;
        }
        return PieceType.BLACK_KNIGHT;
    }

    @Override
    public Score score() {
        return new Score(2.5);
    }
}
