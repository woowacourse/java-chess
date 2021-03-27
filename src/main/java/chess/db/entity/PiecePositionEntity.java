package chess.db.entity;

public class PiecePositionEntity {
    private Long id;
    private PlayerEntity playerEntity;
    private final PieceEntity pieceEntity;
    private final PositionEntity positionEntity;

    public PiecePositionEntity(PieceEntity pieceEntity, PositionEntity positionEntity) {
        this.pieceEntity = pieceEntity;
        this.positionEntity = positionEntity;
    }

    public void setPlayerEntity(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }
}
