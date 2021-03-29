package chess.db.domain.position;

import chess.beforedb.controller.dto.request.MoveRequestDTO;
import chess.beforedb.domain.piece.type.Direction;
import chess.beforedb.domain.player.type.TeamColor;

public class MoveRequestForDB {
    private final TeamColor currentTurnTeamColor;
    private final PositionEntity startPosition;
    private final PositionEntity destination;

    public MoveRequestForDB(TeamColor currentTurnTeamColor, MoveRequestDTO moveRequestDTO) {
        this.currentTurnTeamColor = currentTurnTeamColor;
        startPosition = PositionEntity.of(moveRequestDTO.getStartPosition());
        destination = PositionEntity.of(moveRequestDTO.getDestination());
    }

    public TeamColor getCurrentTurnTeamColor() {
        return currentTurnTeamColor;
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

    public boolean isStartPositionFirstPawnPosition() {
        return startPosition.isFirstPawnPosition(currentTurnTeamColor);
    }
}
