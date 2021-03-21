package chess.domain.piece.kind;

import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

public class Empty extends Piece {
    private static final int EMPTY_SCORE = 0;
    private static final String EMPTY_PIECE_NAME = ".";

    public Empty(Color color, Point point) {
        super(EMPTY_PIECE_NAME, color, point);
    }

    @Override
    public void validateMovable(Direction direction, Piece targetPiece) {
        throw new IllegalArgumentException("비어 있는 칸입니다.");
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return this.point.createNextPoint(direction);
    }

    @Override
    public double score() {
        return EMPTY_SCORE;
    }

    @Override
    public boolean isEmptyPiece() {
        return true;
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
