package chess.domain.piece;

import chess.domain.BoardState;
import chess.domain.MovingDirection;
import chess.domain.player.Player;
import chess.domain.position.Position;
import chess.exception.MovingDirectionException;
import chess.exception.MovingDistanceException;

public abstract class AttackablePawn extends Pawn {

    protected AttackablePawn(Position position, Player player) {
        super(position, player);
    }

    @Override
    protected void validateAttack(Position target, BoardState boardState) {
        MovingDirection direction = MovingDirection.getDirection(position, target);

        if (ATTACK_DIRECTION_BY_PLAYER.get(player).contains(direction)) {
            if (boardState.isEmpty(target)) {
                throw new MovingDirectionException();
            }
            if (position.getRankDifference(target) != direction.getRankDirection()) {
                throw new MovingDistanceException();
            }
        }
    }
}
