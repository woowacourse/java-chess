package chess.dao.entity;

import chess.domain.piece.type.PieceType;
import chess.domain.player.type.TeamColor;
import java.util.Objects;

public class PieceEntity {
    private Long id;
    private final String pieceName;
    private final String color;
    private final PlayerEntity playerEntity;

    public PieceEntity(PieceType pieceType, TeamColor color, PlayerEntity playerEntity) {
        this.pieceName = pieceType.name();
        this.color = color.getValue();
        this.playerEntity = playerEntity;
    }

    public PieceEntity(Long id, String pieceName, String color, PlayerEntity playerEntity) {
        this.id = id;
        this.pieceName = pieceName;
        this.color = color;
        this.playerEntity = playerEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPieceName() {
        return pieceName;
    }

    public String getColor() {
        return color;
    }

    public PlayerEntity getPlayerEntity() {
        return playerEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PieceEntity)) {
            return false;
        }
        PieceEntity that = (PieceEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
