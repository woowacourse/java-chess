package chess.db.dao;

import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.PositionEntity;

public class PiecePositionEntities {
    private PieceEntity pieceEntity;
    private PositionEntity positionEntity;

    public PiecePositionEntities(PieceEntity pieceEntity, PositionEntity positionEntity) {
        this.pieceEntity = pieceEntity;
        this.positionEntity = positionEntity;
    }

    public PieceEntity getPieceEntity() {
        return pieceEntity;
    }

    public void setPieceEntity(PieceEntity pieceEntity) {
        this.pieceEntity = pieceEntity;
    }

    public PositionEntity getPositionEntity() {
        return positionEntity;
    }

    public void setPositionEntity(PositionEntity positionEntity) {
        this.positionEntity = positionEntity;
    }
}
