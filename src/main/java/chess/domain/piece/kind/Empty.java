package chess.domain.piece.kind;

import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

public final class Empty extends Piece {
    private static final int EMPTY_SCORE = 0;
    private static final String EMPTY_PIECE_NAME = ".";

    public Empty(Color color) {
        super(EMPTY_PIECE_NAME, color);
    }

    @Override
    public void validateMovableRoute(Point source, Point target, Piece targetPiece) {
        throw new UnsupportedOperationException("비어 있는 칸입니다.");
    }

    @Override
    public double score() {
        return EMPTY_SCORE;
    }

    @Override
    public boolean isEmptyPiece() {
        return true;
    }
}
