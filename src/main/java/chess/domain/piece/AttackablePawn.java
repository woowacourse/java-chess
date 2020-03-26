package chess.domain.piece;

import chess.domain.MovingDirection;
import chess.domain.player.Player;
import chess.domain.position.Position;
import chess.exception.MovingDirectionException;
import chess.exception.MovingDistanceException;

import java.util.Map;
import java.util.Objects;

public abstract class AttackablePawn extends Pawn {

    protected AttackablePawn(Position position, Player player) {
        super(position, player);
    }

    @Override
    protected void validateAttack(Position target, Map<Position, PieceDto> boardDto) {
        MovingDirection direction = MovingDirection.getDirection(position, target);

        PieceDto pieceDto = boardDto.get(target);
        if (ATTACK_DIRECTION_BY_PLAYER.get(player).contains(direction)) {
            if (Objects.isNull(pieceDto)) {
                throw new MovingDirectionException();
            }
            if (position.getRankDifference(target) != direction.getRankDirection()) {
                throw new MovingDistanceException();
            }
        }
    }
}
