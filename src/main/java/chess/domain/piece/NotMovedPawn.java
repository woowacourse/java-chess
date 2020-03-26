package chess.domain.piece;

import chess.domain.MovingDirection;
import chess.domain.player.Player;
import chess.domain.position.Position;
import chess.exception.MovingDistanceException;
import chess.exception.ObstacleOnPathException;

import java.util.Map;
import java.util.Objects;

public class NotMovedPawn extends AttackablePawn {

    protected NotMovedPawn(Position position, Player player) {
        super(position, player);
    }

    public static NotMovedPawn of(Position position, Player player) {
        return new NotMovedPawn(position, player);
    }

    @Override
    protected void validateMove(Position target, Map<Position, PieceDto> boardDto) {
        MovingDirection movingDirection = MovingDirection.getDirection(position, target);

        if (MOVING_DIRECTION_BY_PLAYER.get(player).equals(movingDirection)) {
            if (position.getRankDifference(target) != movingDirection.getRankDirection() && position.getRankDifference(target) != movingDirection.getRankDirection() * 2) {
                throw new MovingDistanceException();
            }
            Position frontPosition = position.moveByDirection(movingDirection);
            if (!Objects.isNull(boardDto.get(frontPosition))) {
                throw new ObstacleOnPathException();
            }
            PieceDto piece = boardDto.get(target);
            if (!Objects.isNull(piece)) {
                throw new ObstacleOnPathException();
            }
        }
    }

    @Override
    protected PieceState makePieceState() {
        //MovedPawn 리턴
        return MovedPawn.of(position, player);
    }


}
