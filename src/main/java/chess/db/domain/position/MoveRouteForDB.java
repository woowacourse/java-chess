package chess.db.domain.position;

import chess.beforedb.controller.dto.request.MoveRequestDTO;
import chess.beforedb.domain.piece.type.Direction;
import chess.beforedb.domain.player.type.TeamColor;

public class MoveRouteForDB {
    private final PositionEntity startPosition;
    private final PositionEntity destination;

    public MoveRouteForDB(String startPositionInput, String destinationInput) {
        startPosition = PositionEntity.of(startPositionInput);
        destination = PositionEntity.of(destinationInput);
    }

    public MoveRouteForDB(MoveRequestDTO moveRequestDTO) {
        startPosition = PositionEntity.of(moveRequestDTO.getStartPosition());
        destination = PositionEntity.of(moveRequestDTO.getDestination());
    }

    public PositionEntity getStartPosition() {
        return startPosition;
    }

    public PositionEntity getDestination() {
        return destination;
    }

    public Direction getDirection() {
        return startPosition.calculateDirection(destination);
    }

    public PositionEntity getNextPositionOfStartPosition() {
        return startPosition.moveTo(getDirection());
    }

    public boolean isDestination(PositionEntity positionEntity) {
        return positionEntity.equals(destination);
    }

    public boolean isRankForwardedBy(int rankDiff) {
        return startPosition.isRankForwardedBy(destination, rankDiff);
    }

    public boolean isStartPositionFirstPawnPosition(TeamColor teamColor) {
        return startPosition.isFirstPawnPosition(teamColor);
    }
}
