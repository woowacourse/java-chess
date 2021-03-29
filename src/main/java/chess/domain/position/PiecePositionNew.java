package chess.domain.position;

import chess.dao.entity.PositionEntity;
import chess.domain.piece.PieceEntity;
import chess.domain.player.type.TeamColor;

public class PiecePositionNew {
    private final TeamColor teamColor;
    private final Long pieceId;
    private final Long positionId;

    public PiecePositionNew(PieceEntity pieceEntity, PositionEntity positionEntity) {
        positionId = positionEntity.getId();
        if (pieceEntity != null) {
            pieceId = pieceEntity.getId();
            teamColor = pieceEntity.getTeamColor();
            return;
        }
        teamColor = null;
        pieceId = null;
    }

    public Long getPieceId() {
        return pieceId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    public boolean isPieceExists() {
        return pieceId != null;
    }
}
