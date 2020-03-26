package chess.domain.piece;

import chess.domain.MovingDirection;
import chess.domain.player.Player;
import chess.domain.position.Position;
import chess.exception.MovingDistanceException;
import chess.exception.ObstacleOnPathException;

import java.util.Map;
import java.util.Objects;

public class MovedPawn extends AttackablePawn {

    protected MovedPawn(Position position, Player player) {
        super(position, player);
    }

    public static MovedPawn of(Position position, Player player) {
        return new MovedPawn(position, player);
    }

    @Override
    protected void validateMove(Position target, Map<Position, PieceDto> boardDto) {
        MovingDirection movingDirection = MovingDirection.getDirection(position, target);

        if (MOVING_DIRECTION_BY_PLAYER.get(player).equals(movingDirection)) {
            if (position.getRankDifference(target) != movingDirection.getRankDirection()) {
                throw new MovingDistanceException();
            }
            PieceDto piece = boardDto.get(target);
            if (!Objects.isNull(piece)) {
                throw new ObstacleOnPathException();
            }
        }
    }

    @Override
    protected PieceState makePieceState() {
        return this;
    }
}
