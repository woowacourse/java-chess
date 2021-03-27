package chess.db.entity;

import chess.domain.player.type.TeamColor;
import java.util.Objects;

public class PlayerEntity {
    private Long id;
    private final TeamColor teamColor;
    private ChessGameEntity chessGameEntity;

    public PlayerEntity(TeamColor teamColor, ChessGameEntity chessGameEntity) {
        this.teamColor = teamColor;
        this.chessGameEntity = chessGameEntity;
    }

    public PlayerEntity(Long id, TeamColor teamColor, ChessGameEntity chessGameEntity) {
        this.id = id;
        this.teamColor = teamColor;
        this.chessGameEntity = chessGameEntity;
    }

    public Long getId() {
        return id;
    }

    public ChessGameEntity getChessGameEntity() {
        return chessGameEntity;
    }

    public void setChessGameEntity(ChessGameEntity chessGameEntity) {
        this.chessGameEntity = chessGameEntity;
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
