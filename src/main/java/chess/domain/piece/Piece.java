package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.piece.strategy.MoveStrategy;
import chess.domain.postion.Position;

import java.util.List;

public abstract class Piece {

    private final Team team;
    private final MoveStrategy moveStrategy;

    public Piece(final Team team, final MoveStrategy moveStrategy) {
        this.team = team;
        this.moveStrategy = moveStrategy;
    }

    public abstract List<Direction> possibleDirections();

    public abstract String symbol();

    public abstract double score();

    public void canMove(final Position source, final Position target) {
        moveStrategy.isMovable(source, target);
    }

    public boolean isEnemy(Piece other) {
        return !this.team.equals(other.team);
    }

    public void checkPawn(Position source, Position target, Direction direction, Piece other) {}

    public boolean isPawn() {
        return false;
    }

    public Team team() {
        return team;
    }
}
