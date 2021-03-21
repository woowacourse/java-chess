package chess.domain.piece.kind;

import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

public class Bishop extends Piece {
    private static final String BISHOP_NAME = "b";
    private static final int BISHOP_SCORE = 3;

    public Bishop(Color color, Point point) {
        super(BISHOP_NAME, color, point);
    }

    @Override
    public void validateMovable(Direction direction, Piece targetPiece) {
        if (isNotMovable(direction)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
    }

    private boolean isNotMovable(Direction direction) {
        return !Direction.bishopDirection().contains(direction);
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return this.point.createNextPoint(direction);
    }

    @Override
    public double score() {
        return BISHOP_SCORE;
    }

    @Override
    public boolean isEmptyPiece() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }
}
