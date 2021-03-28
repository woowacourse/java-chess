package chess.db.dao;

import chess.beforedb.domain.player.type.TeamColor;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.PositionEntity;
import java.util.Objects;

public class PiecePosition {
    private PieceEntity pieceEntity;
    private PositionEntity positionEntity;

    public PiecePosition(PieceEntity pieceEntity, PositionEntity positionEntity) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PiecePosition)) {
            return false;
        }
        PiecePosition that = (PiecePosition) o;
        return getPieceEntity().equals(that.getPieceEntity())
            && getPositionEntity().equals(that.getPositionEntity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPieceEntity(), getPositionEntity());
    }

    public TeamColor getPieceTeamColor() {
        return pieceEntity.getTeamColor();
    }
}
