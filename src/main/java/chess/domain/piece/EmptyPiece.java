package chess.domain.piece;

import chess.domain.board.position.Position;

import java.util.Collections;

public class EmptyPiece extends Piece {

    private static final EmptyPiece EMPTY_PIECE = new EmptyPiece();

    private EmptyPiece() {
        super(Owner.NONE, Score.ZERO_SCORE, Collections.emptyList(), MaxDistance.EMPTY);
    }

    public static EmptyPiece getInstance() {
        return EMPTY_PIECE;
    }

    @Override
    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public String getSymbol() {
        return ".";
    }
}
