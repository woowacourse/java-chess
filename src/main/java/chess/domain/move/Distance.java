package chess.domain.move;

import chess.domain.board.Position;

public class Distance {

    private final int vertical;
    private final int horizon;

    private Distance(final int vertical, final int horizon) {
        this.vertical = vertical;
        this.horizon = horizon;
    }

    public static Distance of(final Position source, final Position target) {
        return new Distance(source.subtractRow(target), source.subtractColumn(target));
    }

    public int getVertical() {
        return vertical;
    }

    public int getHorizon() {
        return horizon;
    }
}
