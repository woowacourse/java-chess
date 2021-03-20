package chess.domain.piece;

import chess.domain.board.position.Position;

import java.util.Collections;

public class Empty extends Piece {
    private static final int ABLE_DISTANCE_TO_MOVE = 1;

    private static final Empty EMPTY = new Empty();

    private Empty() {
        super(Owner.NONE, new Score(0), Collections.emptyList());
    }

    public static Empty getInstance() {
        return EMPTY;
    }

    @Override
    public boolean isReachable(final Position source, final Position target, final Piece targetPiece) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getSymbol() {
        return ".";
    }

    @Override
    public int getMaxDistance() {
        return ABLE_DISTANCE_TO_MOVE;
    }
}
