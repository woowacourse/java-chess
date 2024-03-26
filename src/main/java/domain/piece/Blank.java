package domain.piece;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.base.ChessPiece;

public class Blank implements ChessPiece {

    private static final Blank instance = new Blank();

    private Blank() {
    }

    public static Blank getInstance() {
        return instance;
    }

    @Override
    public Direction getDirection(Coordinate start, Coordinate destination) {
        throw new IllegalStateException("이동할 말이 존재하지 않습니다.");
    }

    @Override
    public boolean hasSameColor(Color color) {
        return true;
    }
}
