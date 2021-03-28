package chess.db.entity;

import chess.db.dao.PiecePosition;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.PositionEntity;
import java.util.Objects;

public class PlayerPiecePosition {
    private Long id;
    private final PlayerEntity playerEntity;
    private final PiecePosition piecePositionEntities;

    public PlayerPiecePosition(Long id, PlayerEntity playerEntity, PieceEntity pieceEntity,
        PositionEntity positionEntity) {
        this.id = id;
        this.playerEntity = playerEntity;
        this.piecePositionEntities = new PiecePosition(pieceEntity, positionEntity);
    }

    public PlayerPiecePosition(PlayerEntity playerEntity, PieceEntity pieceEntity,
        PositionEntity positionEntity) {
        this.playerEntity = playerEntity;
        this.piecePositionEntities = new PiecePosition(pieceEntity, positionEntity);
    }

    public PlayerPiecePosition(PlayerEntity playerEntity,
        PiecePosition piecePositionEntities) {

        this.playerEntity = playerEntity;
        this.piecePositionEntities = piecePositionEntities;
    }

    public void setPieceEntity(PieceEntity pieceEntity) {
        this.piecePositionEntities.setPieceEntity(pieceEntity);
    }

    public void setPositionEntity(PositionEntity positionEntity) {
        this.piecePositionEntities.setPositionEntity(positionEntity);
    }

    public Long getId() {
        return id;
    }

    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }

    public PieceEntity getPieceEntity() {
        return piecePositionEntities.getPieceEntity();
    }

    public PositionEntity getPositionEntity() {
        return piecePositionEntities.getPositionEntity();
    }

    public PiecePosition getPiecePositionEntities() {
        return piecePositionEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlayerPiecePosition)) {
            return false;
        }
        PlayerPiecePosition that = (PlayerPiecePosition) o;
        return getPlayerEntity().equals(that.getPlayerEntity())
            && getPiecePositionEntities().equals(that.getPiecePositionEntities());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerEntity(), getPiecePositionEntities());
    }
}
