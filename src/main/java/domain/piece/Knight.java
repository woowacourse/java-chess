package domain.piece;

import domain.Color;
import domain.Location;
import java.util.List;

public class Knight extends Piece {

    private Knight(final Color color) {
        super(color);
    }

    public static Knight makeBlack() {
        return new Knight(Color.BLACK);
    }

    public static Knight makeWhite() {
        return new Knight(Color.WHITE);
    }

    private boolean isNotMovable(final Location start, final Location end) {
        if (Math.abs(start.getCol() - end.getCol()) == 1) {
            return Math.abs(start.getRow() - end.getRow()) != 2;
        }
        if (Math.abs(start.getRow() - end.getRow()) == 1) {
            return Math.abs(start.getCol() - end.getCol()) != 2;
        }
        return true;
    }

    @Override
    public List<Location> searchPath(final Location start, final Location end) {
        if (isNotMovable(start, end)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다.");
        }
        return List.of(end);
    }
}
