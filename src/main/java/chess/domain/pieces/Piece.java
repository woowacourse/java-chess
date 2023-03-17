package chess.domain.pieces;

import chess.domain.board.Position;

public abstract class Piece {

    private final Name name;

    public Piece(final Name name) {
        this.name = name;
    }

    public abstract void canMove(final Position start, final Position end);

    public String getName() {
        return name.getName();
    }

    public boolean isPlace() {
        return name.isPlace();
    }

    public boolean isKnight() {
        return this.name.isKnight();
    }

    public boolean isPawn() {
        return this.name.isPawn();
    }

    public boolean isNameLowerCase() {
        return this.name.isLowerCase();
    }

    public boolean isNameUpperCase() {
        return this.name.isUpperCase();
    }
}
