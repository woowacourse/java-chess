package chess.db.entity;

import chess.domain.piece.type.PieceType;
import chess.domain.player.type.TeamColor;
import java.util.Objects;

public class PieceEntity {
    private final Long id;
    private final PieceType pieceType;
    private final TeamColor teamColor;

    public PieceEntity(Long id, String name, String color) {
        this.id = id;
        pieceType = PieceType.valueOf(name);
        teamColor = TeamColor.of(color);
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public TeamColor getTeamColor() {
        return teamColor;
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
        return id.equals(that.id) && getPieceType() == that.getPieceType() && getTeamColor() == that
            .getTeamColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getPieceType(), getTeamColor());
    }
}
