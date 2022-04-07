package chess.domain.piece;

import chess.domain.Board;
import chess.domain.Color;
import chess.domain.position.Position;

public class EmptyPiece extends Piece {

    private static final String NOTATION = ".";
    private static final double SCORE = 0;

    public EmptyPiece() {
        super(Color.NONE);
    }

    @Override
    public void validateMove(Board board, Position source, Position target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
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

    @Override
    public String getNotation() {
        return color.parse(NOTATION);
    }

    @Override
    public double getScore() {
        return SCORE;
    }
}
