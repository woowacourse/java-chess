package chess.domain.piece.kind;

import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

public final class Rook extends Piece {
    private static final int ROOK_SCORE = 5;
    private static final String ROOK_NAME = "r";

    public Rook(Color color) {
        super(ROOK_NAME, color);
    }

    @Override
    public void validateMovableRoute(Point source, Point target, Piece targetPiece) {
        Direction direction = source.findDirection(target);
        if (Direction.isNotRookDirection(direction)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
    }

    @Override
    public double score() {
        return ROOK_SCORE;
    }
}
