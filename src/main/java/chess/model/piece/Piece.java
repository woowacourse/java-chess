package chess.model.piece;

import chess.model.MoveType;
import chess.model.Team;
import chess.model.Turn;
import chess.model.position.Direction;
import chess.model.position.Position;
import chess.model.strategy.MoveStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    private final MoveStrategy moveStrategy;
    protected Team team;

    protected Piece(Team team, MoveStrategy moveStrategy) {
        this.team = team;
        this.moveStrategy = moveStrategy;
    }

    public final boolean isMovable(Position source, Position target, MoveType moveType) {
        return moveStrategy.movable(source, target, moveType);
    }

    public final List<Position> getIntervalPosition(Position source, Position target) {
        Direction direction = Direction.of(source, target);
        List<Position> positions = new ArrayList<>();
        Position next = source;

        while (!next.equals(target)) {
            next = next.getNext(direction);
            positions.add(next);
        }
        positions.remove(target);
        return positions;
    }

    public final boolean isSameTeam(Piece targetPiece) {
        return team.equals(targetPiece.team);
    }

    public final boolean isEnemy(Piece targetPiece) {
        return team.getForwardDirection() + targetPiece.team.getForwardDirection() == 0;
    }

    public final boolean isTeam(Team team) {
        return this.team == team;
    }

    public final boolean isCurrentTurn(Turn turn) {
        return turn.isCurrentTeam(team);
    }

    public boolean isKing() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public abstract double getScore();

    public abstract String getName();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return team == piece.team;
    }

    @Override
    public int hashCode() {
        return Objects.hash(team);
    }

    public Team getTeam() {
        return this.team;
    }
}
