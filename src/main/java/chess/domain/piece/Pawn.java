package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Map;

public abstract class Pawn extends Piece {

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

    protected boolean canPawnMove(final Position source, final Position target) {
        int fileSub = Math.abs(source.fileSub(target));
        int rankSub = Math.abs(source.rankSub(target));
        return (fileSub <= 1 && rankSub == 1) ||
                (fileSub == 0 && rankSub == 2);
    }
}
