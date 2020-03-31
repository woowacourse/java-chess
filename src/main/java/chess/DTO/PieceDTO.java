package chess.DTO;

import chess.domain.Team;
import chess.domain.piece.PieceType;
import chess.domain.position.Position;

public class PieceDTO {
    private Team team;
    private PieceType pieceType;
    private Position position;

    public String getTeam() {
        return team.getName();
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getPieceType() {
        return pieceType.getName();
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public String getPosition() {
        return position.toString();
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
