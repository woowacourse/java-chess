package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.movement.Direction;
import chess.domain.piece.movement.Distance;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private final Owner owner;
    private final List<Direction> ableDirections;
    private final Score score;
    private final Distance ableDistance;
    private final Symbol symbol;

    public Piece(final Owner owner,
                 final Score score,
                 final List<Direction> directions,
                 final Distance distance,
                 final Symbol symbol) {

        this.owner = owner;
        this.score = score;
        this.ableDirections = directions;
        this.ableDistance = distance;
        this.symbol = symbol;
    }

    public final Score score() {
        return this.score;
    }

    public final Owner owner() {
        return this.owner;
    }

    public final boolean isEnemy(final Piece other) {
        return this.owner.isEnemy(other.owner);
    }

    public final boolean isSameTeam(final Piece other) {
        return this.owner.isSameTeam(other.owner);
    }

    public final boolean isOwner(final Owner owner) {
        return this.owner.isSameTeam(owner);
    }

    public boolean isReachable(final Direction direction, final Distance distance, final Position position, final Piece targetPiece) {
        return ableDirections.contains(direction) && distance.isBelow(ableDistance);
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isBlack() {
        return owner.isBlack();
    }

    public Symbol symbol() {
        return symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return owner == piece.owner && symbol == piece.symbol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(owner, symbol);
    }
}
