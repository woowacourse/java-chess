package domain.piece;

import domain.Color;
import domain.InitialColumns;
import domain.Square;

public abstract class Piece {

    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    abstract public void move(Square start, Square destination);
}
