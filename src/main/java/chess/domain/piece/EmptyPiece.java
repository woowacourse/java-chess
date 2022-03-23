package chess.domain.piece;

import chess.domain.Color;

public class EmptyPiece extends Piece {

    private static final String NOTATION = ".";

    public EmptyPiece() {
        super(Color.NONE);
    }

    @Override
    public boolean isBlack() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getNotation() {
        return NOTATION;
    }
}
