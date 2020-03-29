package chess.domain.piece;

import chess.domain.player.Team;

public class PieceDto {

    private PieceType pieceType;
    private Team team;

    public PieceDto(PieceType pieceType, Team team) {
        this.pieceType = pieceType;
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
