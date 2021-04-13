package chess.domain.piece;

import chess.domain.board.position.Position;

import java.util.Collections;

public class EmptyPiece extends Piece {

    private static final int MAX_DISTANCE = 0;

    private static final EmptyPiece EMPTY_PIECE = new EmptyPiece();

    private EmptyPiece() {
        super(Owner.NONE, Score.ZERO_SCORE, Collections.emptyList());
    }

    public static EmptyPiece getInstance() {
        return EMPTY_PIECE;
    }

    @Override
    public int maxDistance() {
        return MAX_DISTANCE;
    }

    @Override
    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isEmptyPiece() {
        return true;
    }

    @Override
    public String getSymbol() {
        return ".";
    }
}
