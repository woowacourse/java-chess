package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Piece {

    private final String name;
    private final List<Direction> directions;
    protected final Team team;

    public Piece(String name, List<Direction> directions, Team team) {
        this.name = name;
        this.directions = directions;
        this.team = team;
    }

    public void movable(Position from, Position to, Piece toPiece) {
        Direction direction = from.findDirection(to, this);
        checkDirection(direction);
        checkDifferentTeam(this, toPiece);
    }

    private void checkDirection(Direction direction) {
        if (cannotFindSameDirection(direction)) {
            throw new IllegalArgumentException(getName() + " 가 움직일 수 있는 방향이 아닙니다.");
        }
    }

    private boolean cannotFindSameDirection(Direction direction) {
        return !directions.contains(direction);
    }

    private void checkDifferentTeam(Piece from, Piece to) {
        if (Objects.nonNull(to) && from.isSameTeam(to)) {
            throw new IllegalArgumentException("도착 지점에 아군 말이 있어 이동이 불가능합니다.");
        }
    }

    public final List<Position> findMovablePosition(Position now) {
        return directions.stream()
                .filter(now::isMovable)
                .map(now::move)
                .collect(Collectors.toUnmodifiableList());
    }

    public abstract float getScore();

    public final String getName() {
        return name;
    }

    public final boolean isSameTeam(Piece piece) {
        return piece.team == this.team;
    }

    public final boolean isSameTeam(Team team) {
        return team == this.team;
    }

    public Direction findDirection(Position from, Position to) {
        return from.findDirection(to, this);
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isBlank() {
        return false;
    }

    public boolean isOneStep() {
        return true;
    }

    @Override
    public String toString() {
        return team.toString() + "_" + name;
    }
}
