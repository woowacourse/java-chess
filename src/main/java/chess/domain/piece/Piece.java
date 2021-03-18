package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;

public abstract class Piece {
    private Position position;
    private final boolean isBlack;

    public Piece(final boolean isBlack, final char x, final char y) {
        this.position = new Position(x, y);
        this.isBlack = isBlack;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public Position getPosition() {
        return position;
    }

    public void moveTo(Position position) {
        this.position = position;
    }

    public boolean isEmpty() {
        return this instanceof Empty;
    }

    public boolean isSameColor(Piece other) {
        return this.isBlack == other.isBlack;
    }

    public abstract List<Direction> getDirections();

    public abstract int getStepRange();

    public abstract char getName();
}