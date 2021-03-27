package chess.db.entity;

import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.PositionEntity;
import java.util.Objects;

public class PlayerPiecePositionEntity {
    private Long id;
    private final PlayerEntity playerEntity;
    private PieceEntity pieceEntity;
    private PositionEntity positionEntity;

    public PlayerPiecePositionEntity(Long id, PlayerEntity playerEntity, PieceEntity pieceEntity,
        PositionEntity positionEntity) {
        this.id = id;
        this.playerEntity = playerEntity;
        this.pieceEntity = pieceEntity;
        this.positionEntity = positionEntity;
    }

    public PlayerPiecePositionEntity(PlayerEntity playerEntity, PieceEntity pieceEntity,
        PositionEntity positionEntity) {
        this.playerEntity = playerEntity;
        this.pieceEntity = pieceEntity;
        this.positionEntity = positionEntity;
    }

    public void setPieceEntity(PieceEntity pieceEntity) {
        this.pieceEntity = pieceEntity;
    }

    public void setPositionEntity(PositionEntity positionEntity) {
        this.positionEntity = positionEntity;
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
