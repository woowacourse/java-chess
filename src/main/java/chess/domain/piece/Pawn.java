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
        if (source.isFileEquals(target)) {
            return isEmptyPosition.keySet()
                    .stream()
                    .allMatch(isEmptyPosition::get);
        }
        return !isEmptyPosition.get(target);
    }

    protected boolean canPawnMove(final Position source, final Position target, final int direction) {
        int fileSub = source.fileSub(target) * direction;
        int rankSub = source.rankSub(target) * direction;
        return (fileSub <= 1 && rankSub == 1) ||
                (fileSub == 0 && rankSub == 2);
    }

    // white pawn : 위로 간다.
    // black pawn : 아래로 간다.
}
