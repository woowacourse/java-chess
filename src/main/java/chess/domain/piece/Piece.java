package chess.domain.piece;

import chess.domain.*;
import chess.domain.exceptions.InvalidDirectionException;
import chess.domain.exceptions.InvalidDistanceException;

import java.util.List;
import java.util.Objects;

public abstract class Piece {
    protected static final int LIMIT_DISTANCE_ONE = 1;

    private final Team team;
    private MoveRule moveRule;

    public Piece(Team team) {
        this.team = team;
        this.moveRule = setMoveRule();
    }

    public Piece(Team team, MoveRule moveRule) {
        this.team = team;
        this.moveRule = moveRule;
    }

    public boolean isSameTeam(Piece piece) {
        return Team.isSameTeam(this.team, piece.team);
    }

    public Team getTeam() {
        return team;
    }

    public Team getEnemy() {
        return team.enemy();
    }

    public boolean isTurn(Turn turn) {
        return turn.isTurn(team);
    }

    public boolean canMove(Position source, Position target) {
        return moveRule.check(source, target);
    }

    public double score(final int count) {
        return count * getScore();
    }

    public boolean isKing() {
        return false;
    }

    protected void validDistance(final int distance, final int limit) {
        if (distance != limit) {
            throw new InvalidDistanceException("움직일 수 있는 거리가 아닙니다.");
        }
    }

    protected void validDirection(final List<Direction> movables, final Direction direction) {
        if (movables.stream().noneMatch(movable -> movable == direction)) {
            throw new InvalidDirectionException("움직일 수 있는 방향이 아닙니다.");
        }
    }

    public abstract String getName();
    public abstract double getScore();
    protected abstract MoveRule setMoveRule();

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Piece piece = (Piece) o;
        return team == piece.team &&
                Objects.equals(moveRule, piece.moveRule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, moveRule);
    }
}