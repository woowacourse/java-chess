package domain.piece.base;

import domain.coordinate.Coordinate;
import domain.piece.Color;
import java.util.List;

public abstract class ChessPieceBase implements ChessPiece {

    protected final Color color;

    public ChessPieceBase(Color color) {
        this.color = color;
    }

    @Override
    public abstract List<Integer> getDirection(Coordinate start, Coordinate destination, boolean canAttack);

    @Override
    public boolean isNotSameColor(Color color) {
        return this.color != color;
    }

    @Override
    public boolean isBlack() {
        return color == Color.BLACK;
    }
}
