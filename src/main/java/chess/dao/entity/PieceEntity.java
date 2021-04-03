package chess.dao.entity;

import chess.domain.piece.type.PieceType;
import chess.domain.player.type.TeamColor;

public class PieceEntity {
    private final Long id;
    private final PieceType pieceType;
    private final TeamColor teamColor;

    public PieceEntity(Long id, String name, String colorValue) {
        this.id = id;
        this.pieceType = PieceType.find(name);
        this.teamColor = TeamColor.of(colorValue);
    }

    public Long getId() {
        return id;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public TeamColor getTeamColor() {
        return teamColor;
    }
}
