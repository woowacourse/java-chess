package chess.domain.piece;

import chess.domain.board.position.Path;
import chess.domain.board.position.Position;
import chess.domain.direction.Direction;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Piece {
    private final Owner owner;
    private final Score score;
    private final List<Direction> directions;
    private final MaxDistance maxDistance;

    public Piece(Owner owner, Score score, List<Direction> directions, MaxDistance maxDistance) {
        this.owner = owner;
        this.directions = directions;
        this.score = score;
        this.maxDistance = maxDistance;
    }

    public Piece(Owner owner, Score score, List<Direction> directions) {
        this(owner, score, directions, MaxDistance.EMPTY);
    }

    public List<Path> ableToPath(final Position source) {
        return directions.stream()
                .map(direction -> ableToPathDirection(source, direction))
                .collect(Collectors.toList());
    }

    public Path ableToPathDirection(Position source, Direction direction) {
        List<Position> path = new ArrayList<>();
        Position target = source;
        while (target.isValidPosition(direction) && isValidDistance(source, target.next(direction))) {
            target = target.next(direction);
            path.add(target);
        }
        return Path.of(path);
    }

    private boolean isValidDistance(Position source, Position target) {
        return source.getDistance(target) <= this.maxDistanceValue();
    }

    public final boolean isSameTeam(final Piece other) {
        return this.owner.isSameTeam(other.owner);
    }

    public boolean isSameOwner(final Owner owner) {
        return this.owner.isSameTeam(owner);
    }

    public boolean isSameOwnerPawn(Owner owner) {
        return this.isPawn() && this.owner.isSameTeam(owner);
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }

    public final boolean isEnemy(final Piece other) {
        return this.owner.isEnemy(other.owner);
    }

    public final Score score() {
        return this.score;
    }

    public int maxDistanceValue() {
        return this.maxDistance.value();
    }

    public abstract boolean isReachable(final Position source, final Position target, final Piece targetPiece);

    public abstract String getSymbol();
}
