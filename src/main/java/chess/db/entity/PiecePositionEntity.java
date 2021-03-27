package chess.db.entity;

import java.util.Objects;

public class PiecePositionEntity {
    private Long id;
    private PlayerEntity playerEntity;
    private final PieceEntity pieceEntity;
    private final PositionEntity positionEntity;

    public PiecePositionEntity(PieceEntity pieceEntity, PositionEntity positionEntity) {
        this.pieceEntity = pieceEntity;
        this.positionEntity = positionEntity;
    }

    public PiecePositionEntity(Long id, PlayerEntity playerEntity, PieceEntity pieceEntity, PositionEntity positionEntity) {
        this.id = id;
        this.playerEntity = playerEntity;
        this.pieceEntity = pieceEntity;
        this.positionEntity = positionEntity;
    }

    public PiecePositionEntity(Long id) {
        this.id = id;
        pieceEntity = null;
        positionEntity = null;
    }

    public Long getId() {
        return id;
    }

    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }

    public PieceEntity getPieceEntity() {
        return pieceEntity;
    }

    public PositionEntity getPositionEntity() {
        return positionEntity;
    }

    public void setPlayerEntity(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PiecePositionEntity)) {
            return false;
        }
        PiecePositionEntity that = (PiecePositionEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
