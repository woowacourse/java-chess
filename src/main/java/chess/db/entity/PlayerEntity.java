package chess.db.entity;

import chess.domain.player.type.TeamColor;
import java.util.List;
import java.util.Objects;

public class PlayerEntity {
    private Long id;
    private final TeamColor teamColor;
    private ChessGameEntity chessRoomEntity;

    public PlayerEntity(ChessGameEntity chessRoomEntity) {
        this.chessRoomEntity = chessRoomEntity;
        this.teamColor = null;
    }

    public PlayerEntity(TeamColor teamColor) {
        this.teamColor = teamColor;
    }

    public PlayerEntity(Long id, ChessGameEntity chessRoomEntity) {
        this.id = id;
        this.chessRoomEntity = chessRoomEntity;
        this.teamColor = null;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setPieceEntities(List<PiecePositionEntity> initialPiecesPositionsByColor) {
        for (PiecePositionEntity piecePositionEntity : initialPiecesPositionsByColor) {
            piecePositionEntity.setPlayerEntity(this);
        }
    }

    public ChessGameEntity getChessRoomEntity() {
        return chessRoomEntity;
    }

    public void addChessRoomEntity(ChessGameEntity chessRoomEntity) {
        this.chessRoomEntity = chessRoomEntity;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlayerEntity)) {
            return false;
        }
        PlayerEntity that = (PlayerEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
