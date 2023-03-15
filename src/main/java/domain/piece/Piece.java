package domain.piece;

import domain.chessboard.SquareStatus;

public abstract class Piece implements SquareStatus {

    private final Color color;

    public Piece(final Color color) {
        this.color = color;
    }

    public abstract boolean findRoute();
}
