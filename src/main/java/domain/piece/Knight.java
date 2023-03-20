package domain.piece;

import domain.Location;
import domain.type.Color;
import java.util.List;

public class Knight extends Piece {

    private Knight(final Color color) {
        super(color, PieceType.KNIGHT);
    }

    public static Knight makeBlack() {
        return new Knight(Color.BLACK);
    }

    public static Knight makeWhite() {
        return new Knight(Color.WHITE);
    }

    private boolean isNotMovable(final Location start, final Location end) {
        if (Math.abs(start.getColumn() - end.getColumn()) == 1) {
            return Math.abs(start.getRow() - end.getRow()) != 2;
        }
        if (Math.abs(start.getRow() - end.getRow()) == 1) {
            return Math.abs(start.getColumn() - end.getColumn()) != 2;
        }
        return true;
    }

    @Override
    public List<Location> searchPath(final Location start, final Location end) {
        if (isNotMovable(start, end)) {
            throw new IllegalArgumentException(IMPOSSIBLE_MOVE_ERROR_MESSAGE);
        }
        return List.of(end);
    }
}
