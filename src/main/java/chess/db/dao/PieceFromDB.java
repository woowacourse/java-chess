package chess.db.dao;

import chess.beforedb.domain.piece.type.PieceType;
import chess.beforedb.domain.player.type.TeamColor;

public class PieceFromDB {
    private final Long id;
    private final PieceType pieceType;
    private final TeamColor teamColor;

    public PieceFromDB(Long id, String name, String color) {
        this.id = id;
        this.pieceType = PieceType.find(name);
        this.teamColor = TeamColor.of(color);
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
