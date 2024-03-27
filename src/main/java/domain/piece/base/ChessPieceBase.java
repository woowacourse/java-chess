package domain.piece.base;

import domain.coordinate.Coordinate;
import domain.direction.Direction;
import domain.piece.Color;

public abstract class ChessPieceBase implements ChessPiece {

    protected final Color color;

    public ChessPieceBase(Color color) {
        this.color = color;
    }

    @Override
    public abstract Direction getDirection(Coordinate start, Coordinate destination, boolean canAttack);

    @Override
    public boolean isOpponentColor(Color color) {
        return this.color != color;
    }

    @Override
    public boolean isBlack() {
        return color == Color.BLACK;
    }
}
