package chess.dto;

import chess.domain.piece.Piece;

public class PieceDto {
    private final String team;
    private final String type;

    public PieceDto(Piece piece) {
        this.team = piece.getTeam().name().toLowerCase();
        this.type = piece.getInfo().getType();
    }

    public String getTeam() {
        return team;
    }

    public String getType() {
        return type;
    }
}
