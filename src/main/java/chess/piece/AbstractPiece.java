package chess.piece;

import chess.game.MoveCommand;
import chess.position.Position;

public abstract class AbstractPiece implements Piece {

    private final Name name;
    private final Color color;

    AbstractPiece(final Name name, final Color color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public double getScore() {
        return 0;
    }

    @Override
    public final boolean isSameTeam(final Piece other) {
        return this.color.hasSameColor(other.getColor());
    }

    @Override
    public boolean canMove(MoveCommand command) {
        return true;
    }

    @Override
    public Direction getDirection(final Position from, final Position to) {
        return Direction.NONE;
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
}
