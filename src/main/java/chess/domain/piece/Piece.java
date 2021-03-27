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

    public abstract boolean isReachable(final Position source, final Position target, final Piece targetPiece);

    public final Score score() {
        return this.score;
    }

    public boolean isSameOwnerPawn(Owner owner) {
        return this.isPawn() && this.owner.isSameTeam(owner);
    }

    public final boolean isEnemy(final Piece other) {
        return this.owner.isEnemy(other.owner);
    }

    public final boolean isSameTeam(final Piece other) {
        return this.owner.isSameTeam(other.owner);
    }

    public boolean isOwner(final Owner owner) {
        return this.owner.isSameTeam(owner);
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public abstract String getSymbol();

    public List<Direction> getDirections() {
        return Collections.unmodifiableList(directions);
    }

    public abstract int getMaxDistance();
}
