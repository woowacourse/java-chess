package chess.domain.piece;

import chess.domain.board.position.Position;
import chess.domain.piece.movement.Direction;
import chess.domain.piece.movement.Distance;

import java.util.List;

public abstract class Piece {
    private final Owner owner;
    private final List<Direction> ableDirections;
    private final Score score;
    private final Distance ableDistance;
    private final String symbol;

    // XXX :: 빌더 패턴 공부하기
    // XXX :: Symbol은 view로 빼기
    public Piece(final Owner owner,
                 final Score score,
                 final List<Direction> directions,
                 final Distance distance,
                 final String symbol) {

        this.owner = owner;
        this.score = score;
        this.ableDirections = directions;
        this.ableDistance = distance;
        this.symbol = symbol;
    }

    public final Score score() {
        return this.score;
    }

    public final String getSymbol() {
        return symbol;
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
}
