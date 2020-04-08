package chess.domain.piece;

import chess.controller.dto.PieceDto;
import chess.domain.game.MovingDirection;
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
            validateDistance(target, movingDirection);
            validateObstacle(boardDto, movingDirection);
            validateObstacle(target, boardDto);
        }
    }

    private void validateObstacle(Position target, Map<Position, PieceDto> boardDto) {
        PieceDto piece = boardDto.get(target);
        if (!Objects.isNull(piece)) {
            throw new ObstacleOnPathException();
        }
    }

    private void validateObstacle(Map<Position, PieceDto> boardDto, MovingDirection movingDirection) {
        Position frontPosition = position.moveByDirection(movingDirection);
        if (!Objects.isNull(boardDto.get(frontPosition))) {
            throw new ObstacleOnPathException();
        }
    }

    private void validateDistance(Position target, MovingDirection movingDirection) {
        if (position.getRankDifference(target) != movingDirection.getRankDirection() && position.getRankDifference(target) != movingDirection.getRankDirection() * 2) {
            throw new MovingDistanceException();
        }
    }

    @Override
    protected PieceState makePieceState() {
        //MovedPawn 리턴
        return MovedPawn.of(position, player);
    }


}
