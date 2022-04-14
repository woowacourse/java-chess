package chess.model.strategy;

import chess.model.MoveType;
import chess.model.position.Direction;
import chess.model.position.Distance;
import chess.model.position.Position;

import java.util.List;

public class PawnMoveStrategy implements MoveStrategy {
    private final List<Direction> moveDirections;
    private final List<Direction> attackDirections;

    public PawnMoveStrategy(List<Direction> moveDirections, List<Direction> attackDirections) {
        this.moveDirections = moveDirections;
        this.attackDirections = attackDirections;
    }

    @Override
    public boolean movable(Position source, Position target, MoveType moveType) {
        Direction direction = Direction.of(source, target);
        Distance distance = Distance.of(source, target, direction);

        if (moveType.equals(MoveType.ATTACK)) {
            return attackDirections.contains(direction) && Distance.oneStep().contains(distance);
        }
        if (source.isInitPawn(direction)) {
            return moveDirections.contains(direction) && Distance.oneAndTwoStep().contains(distance);
        }
        return moveDirections.contains(direction) && Distance.oneStep().contains(distance);
    }
}
