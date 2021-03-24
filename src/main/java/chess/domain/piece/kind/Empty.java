package chess.domain.piece.kind;

import chess.domain.piece.Color;

public class Empty extends Piece {
    private static final int EMPTY_SCORE = 0;
    private static final String EMPTY_PIECE_NAME = ".";

    public Empty(Color color) {
        super(EMPTY_PIECE_NAME, color);
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
