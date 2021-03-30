package chess.domain.piece;

import chess.domain.board.position.Position;

import java.util.Collections;

public class Empty extends Piece {

    private static final Empty EMPTY = new Empty();

    private Empty() {
        super(Owner.NONE, new Score(0), Collections.emptyList(), MaxDistance.EMPTY);
    }

    public static Empty getInstance() {
        return EMPTY;
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
