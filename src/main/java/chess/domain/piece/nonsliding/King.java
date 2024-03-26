package chess.domain.piece.nonsliding;

import chess.score.Score;
import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import java.util.Set;

public final class King extends NonSlidingPiece {
    private static final Set<Direction> DIRECTIONS = Direction.getEightDirection();

    public King(Color color) {
        super(color, DIRECTIONS);
    }

    public static King createWhiteKing() {
        return new King(Color.WHITE);
    }

    public static King createBlackKing() {
        return new King(Color.BLACK);
    }

    @Override
    public PieceType pieceType() {
        if (color == Color.WHITE) {
            return PieceType.WHITE_KING;
        }
        return PieceType.BLACK_KING;
    }

    @Override
    public Score score() {
        return new Score(0);
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
