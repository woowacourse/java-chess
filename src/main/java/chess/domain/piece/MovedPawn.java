package chess.domain.piece;

import chess.domain.BoardState;
import chess.domain.MovingDirection;
import chess.domain.player.Player;
import chess.domain.position.Position;
import chess.exception.MovingDistanceException;
import chess.exception.ObstacleOnPathException;

public class MovedPawn extends AttackablePawn {

    protected MovedPawn(Position position, Player player) {
        super(position, player);
    }

    public static MovedPawn of(Position position, Player player) {
        return new MovedPawn(position, player);
    }

    @Override
    protected void validateMove(Position target, BoardState boardState) {
        MovingDirection movingDirection = MovingDirection.getDirection(position, target);

        if (MOVING_DIRECTION_BY_PLAYER.get(player).equals(movingDirection)) {
            if (position.getRankDifference(target) != movingDirection.getRankDirection()) {
                throw new MovingDistanceException();
            }
            if (boardState.isNotEmpty(target)) {
                throw new ObstacleOnPathException();
            }
        }
    }

    @Override
    protected PieceState makePieceState() {
        return this;
    }
}
