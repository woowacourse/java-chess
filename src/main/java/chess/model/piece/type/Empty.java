package chess.model.piece.type;

import chess.model.piece.Camp;
import chess.model.piece.Piece;
import chess.model.position.Distance;

public class Empty extends Piece {

    public static final Piece EMPTY_PIECE = new Empty(Camp.BLACK);

    private Empty(final Camp camp) {
        super(camp);
    }

    @Override
    public Piece pick() {
        return this;
    }

    @Override
    public boolean movable(final Distance ignoredDistance, final Piece ignoredTarget) {
        return false;
    }

    @Override
    public boolean isSameTeam(final Camp camp) {
        return false;
    }

    @Override
    public boolean isNotPassable() {
        return false;
    }
}
