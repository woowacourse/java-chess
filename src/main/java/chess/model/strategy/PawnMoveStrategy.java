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

        /*
        if (죽이려고 함):
            방향이 대각선이어야함
            거리가 1이어야함
        else (그냥 이동일 경우):
            if (첫번째 이동인 경우):
                방향이 직진이어야함
                거리가 1 또는 2여야 함
            else (n번째 이동일 경우 n>1):
                방향이 직진이어야함
                거리가 1이어야함
         */
    }
}
