package chess.domain.piece.sliding;

import chess.score.Score;
import chess.domain.color.Color;
import chess.domain.piece.Direction;
import chess.domain.piece.PieceType;
import java.util.Set;

public final class Queen extends SlidingPiece {
    private static final Set<Direction> directions = Direction.getEightDirection();

    public Queen(Color color) {
        super(color, directions);
    }

    @Override
    public PieceType pieceType() {
        if (color == Color.WHITE) {
            return PieceType.WHITE_QUEEN;
        }
        return PieceType.BLACK_QUEEN;
    }

    @Override
    public Score score() {
        return new Score(9);
    }
}
