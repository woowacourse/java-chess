package chess.domain.piece.kind;

import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

public final class Bishop extends Piece {
    private static final String BISHOP_NAME = "b";
    private static final int BISHOP_SCORE = 3;

    public Bishop(Color color, Point point) {
        super(BISHOP_NAME, color, point);
    }

    @Override
    public void validateMovable(Direction direction, Piece targetPiece) {
        if (Direction.isNotBishopDirection(direction)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return this.point.createNextPoint(direction);
    }

    @Override
    public double score() {
        return BISHOP_SCORE;
    }
}
