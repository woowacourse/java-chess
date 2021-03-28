package chess.db.entity;

import chess.db.dao.PiecePositionEntities;
import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.PositionEntity;
import java.util.Objects;

public class PlayerPiecePositionEntity {
    private Long id;
    private final PlayerEntity playerEntity;
    private final PiecePositionEntities piecePositionEntities;

    public PlayerPiecePositionEntity(Long id, PlayerEntity playerEntity, PieceEntity pieceEntity,
        PositionEntity positionEntity) {
        this.id = id;
        this.playerEntity = playerEntity;
        this.piecePositionEntities = new PiecePositionEntities(pieceEntity, positionEntity);
    }

    public PlayerPiecePositionEntity(PlayerEntity playerEntity, PieceEntity pieceEntity,
        PositionEntity positionEntity) {
        this.playerEntity = playerEntity;
        this.piecePositionEntities = new PiecePositionEntities(pieceEntity, positionEntity);
    }

    public PlayerPiecePositionEntity(PlayerEntity playerEntity,
        PiecePositionEntities piecePositionEntities) {

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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlayerPiecePositionEntity)) {
            return false;
        }
        PlayerPiecePositionEntity that = (PlayerPiecePositionEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
