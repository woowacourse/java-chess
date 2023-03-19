package domain.piece;

import domain.Location;
import domain.type.Color;
import domain.type.PieceType;

public final class Queen extends Piece {

    private Queen(final Color color) {
        super(color, PieceType.QUEEN);
    }

    public static Queen makeBlack() {
        return new Queen(Color.BLACK);
    }

    public static Queen makeWhite() {
        return new Queen(Color.WHITE);
    }

    @Override
    protected boolean isNotMovable(final Location start, final Location end) {
        return start.isNotSameLine(end) && start.isNotDiagonal(end);
    }
}
