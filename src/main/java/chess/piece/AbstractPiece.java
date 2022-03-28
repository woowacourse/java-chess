package chess.piece;

import chess.game.Position;

public abstract class AbstractPiece implements Piece {

    private final Name name;
    private final Color color;

    AbstractPiece(final Name name, final Color color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public abstract boolean canMove(final Position from, final Position to);

    @Override
    public abstract double getScore();

    @Override
    public final boolean isSameTeam(final Piece other) {
        return color.hasSameColor(other.getColor());
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKnight() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public final Name getName() {
        return name;
    }

    @Override
    public final Color getColor() {
        return color;
    }
}
