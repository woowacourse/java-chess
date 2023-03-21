package chess.domain.pieces;

import chess.domain.board.Position;

public abstract class Piece {

    protected final Name name;
    protected Score score;

    public Piece(final Name name) {
        this.name = name;
    }

    public abstract void canMove(final Position start, final Position end);

    public String getName() {
        return name.getName();
    }

    public boolean isPlace() {
        return false;
    }

    public boolean isKnight() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isNameLowerCase() {
        return this.name.isLowerCase();
    }

    public boolean isNameUpperCase() {
        return this.name.isUpperCase();
    }

    public double getScore() {
        return score.getScore();
    }

    public void updateScoreByOtherPawnsBeingWithSameColumn() {
    }
}
