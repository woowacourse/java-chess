package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.direction.Direction;

import java.util.Collections;
import java.util.List;

public abstract class Piece {
    protected final Owner owner;
    protected final List<Direction> directions;
    protected final Score score;

    public Piece(final Owner owner, final Score score, final List<Direction> directions) {
        this.owner = owner;
        this.score = score;
        this.directions = directions;
    }

    public abstract boolean validateMove(final Position source, final Position target, final Piece targetPiece);

    public final Score score() {
        return this.score;
    }

    ;

    public final String decideUpperOrLower(final String symbol) {
        if (owner.equals(Owner.BLACK)) {
            return symbol.toUpperCase();
        }

        if (owner.equals(Owner.WHITE)) {
            return symbol.toLowerCase();
        }

        return symbol;
    }

    public abstract String getSymbol();

    public final boolean isEnemy(final Piece other) {
        return this.owner.isEnemy(other.owner);
    }

    public final boolean isSameTeam(final Piece other) {
        return this.owner.isSameTeam(other.owner);
    }

    public List<Direction> getDirections() {
        return Collections.unmodifiableList(directions);
    }

    public boolean isOwner(final Owner owner) {
        return this.owner.isSameTeam(owner);
    }

    public abstract int getMaxDistance();

    public boolean isKing() {
        return this instanceof King;
    }
}
