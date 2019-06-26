package chess.domain.piece;

import chess.domain.*;
import chess.domain.exceptions.IllegalTargetException;
import chess.domain.exceptions.InvalidDirectionException;
import chess.domain.exceptions.InvalidDistanceException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public abstract class Piece {
    static final int LIMIT_DISTANCE_ONE_UNIT = 1;

    private final Team team;
    private MoveRule moveRule;

    public Piece(Team team) {
        this.team = team;
        this.moveRule = setMoveRule();
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

    public boolean canMove(Position source, Position target, Optional<Team> optionalTargetPieceTeam) {
        return moveRule.check(source, target, optionalTargetPieceTeam);
    }

    public double score(final int count) {
        return count * getScore();
    }

    public boolean isKing() {
        return false;
    }

    void validSameTeamCatch(Optional<Team> optionalTargetPieceTeam) {
        Team targetPieceTeam = optionalTargetPieceTeam.orElseGet(() -> getTeam().enemy());
        if (Team.isSameTeam(getTeam(), targetPieceTeam)) {
            throw new IllegalTargetException("같은 팀이 있는 위치로 이동이 불가능합니다.");
        }
    }

    ;

    void validDistance(final int distance, final int limit) {
        if (distance > limit) {
            throw new InvalidDistanceException("움직일 수 있는 거리가 아닙니다.");
        }
    }

    void validDirection(final List<Direction> movables, final Direction direction) {
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
        return team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }
}