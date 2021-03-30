package chess.dao.entity;

public class GamePiecePositionEntity {
    private final Long playerPiecePositionId;
    private Long positionId;

    public GamePiecePositionEntity(Long playerPiecePositionId, Long positionId) {
        this.playerPiecePositionId = playerPiecePositionId;
        this.positionId = positionId;
    }

    public Long getPlayerPiecePositionId() {
        return playerPiecePositionId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
}
