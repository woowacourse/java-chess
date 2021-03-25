package chess.domain.piece.kind;

import chess.domain.Point;
import chess.domain.piece.Color;

public final class King extends Piece {
    private static final int KING_SCORE = 0;
    private static final String KING_NAME = "k";

    public King(Color color) {
        super(KING_NAME, color);
    }

    @Override
    public void validateMovableRoute(Point source, Point target, Piece targetPiece) {
        validateTargetPieceColor(targetPiece);
        int distance = source.calculateDistance(target);

        if (distance != MOVE_STRAIGHT_ONE_SQUARE && distance != MOVE_DIAGONAL_ONE_SQUARE) {
            throw new IllegalArgumentException("기물이 이동할 수 없는 경로입니다.");
        }
    }

    @Override
    public double score() {
        return KING_SCORE;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
