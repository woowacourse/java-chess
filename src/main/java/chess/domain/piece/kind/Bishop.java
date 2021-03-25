package chess.domain.piece.kind;

import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

public final class Bishop extends Piece {
    private static final String BISHOP_NAME = "b";
    private static final int BISHOP_SCORE = 3;

    public Bishop(Color color) {
        super(BISHOP_NAME, color);
    }

    @Override
    public void validateMovableRoute(Point source, Point target, Piece targetPiece) {
        validateTargetPieceColor(targetPiece);
        Direction direction = source.findDirection(target);

        if (Direction.isNotBishopDirection(direction)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
    }

    @Override
    public double score() {
        return BISHOP_SCORE;
    }
}
