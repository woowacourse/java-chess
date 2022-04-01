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

    public Direction direction(final Position source, final Position target, final Piece pieceInTarget) {
        validateSameTeamInTarget(pieceInTarget);

        moveStrategy.isMovable(source, target);
        Direction direction = Direction.beMoveDirection(possibleDirections(), source, target);
        checkPawn(source, target, direction, pieceInTarget);

        return direction;
    }

    private void validateSameTeamInTarget(final Piece pieceInTarget) {
        if (!isEnemy(pieceInTarget)) {
            throw new IllegalArgumentException("목적지에 같은 팀 기물이 있습니다.");
        }
    }

    public boolean isEnemy(Piece other) {
        return !team.equals(other.team);
    }

    protected void checkPawn(Position source, Position target, Direction direction, Piece other) {
    }

    public Team team() {
        return team;
    }
}
