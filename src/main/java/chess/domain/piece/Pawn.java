package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Map;

public abstract class Pawn extends Piece {

    protected static final int DOWN = 1;
    protected static final int UP = -1;

    public Pawn(Color color) {
        super(color);
    }

    public boolean canMove(final Map<Position, Boolean> isEmptyPosition, final Position source, final Position target) {
        if (source.isSameFile(target)) {
            return isEmptyPosition.keySet()
                    .stream()
                    .allMatch(isEmptyPosition::get);
        }
        return !isEmptyPosition.get(target);
    }

    protected boolean canPawnMove(final Position source, final Position target, final int direction) {
        int fileSub = source.fileSub(target) * direction;
        int rankSub = source.rankSub(target) * direction;

        return (fileSub <= ONE_SQUARES && rankSub == ONE_SQUARES) ||
                (fileSub == SAME_SQUARE && rankSub == TWO_SQUARES);
    }
}
