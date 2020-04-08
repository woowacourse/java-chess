package chess.dto;

import chess.domain.Team;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

public class PieceDto {
    private Position position;
    private Team team;
    private PieceType pieceType;

    public PieceDto(Position position, Team team, PieceType pieceType) {
        this.position = position;
        this.team = team;
        this.pieceType = pieceType;
    }

    public String getPosition() {
        return position.toString();
    }

    public String getTeam() {
        return team.getName();
    }

    public String getPieceType() {
        return pieceType.getName();
    }
}
