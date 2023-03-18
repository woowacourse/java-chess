package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Map;

public abstract class Pawn extends Piece{

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
}
