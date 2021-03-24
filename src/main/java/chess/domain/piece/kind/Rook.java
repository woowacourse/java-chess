package chess.domain.piece.kind;

import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

public final class Rook extends Piece {
    private static final int ROOK_SCORE = 5;
    private static final String ROOK_NAME = "r";

    public Rook(Color color, Point point) {
        super(ROOK_NAME, color, point);
    }

    @Override
    public void validateMovable(Direction direction, Piece targetPiece) {
        if (Direction.isNotRookDirection(direction)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return this.point.createNextPoint(direction);
    }

    @Override
    public double score() {
        return ROOK_SCORE;
    }
}
