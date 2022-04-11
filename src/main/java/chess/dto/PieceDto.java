package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class PieceDto {

    private final String team;
    private final String type;
    private final String position;

    public PieceDto(String team, String type, String position) {
        this.team = team;
        this.type = type;
        this.position = position;
    }

    public static PieceDto of(Piece piece, Position position) {
        String team = piece.getTeam().name().toLowerCase();
        String type = piece.getInfo().getType();
        String positionName = position.getName();

        return new PieceDto(team, type, positionName);
    }

    public String getTeam() {
        return team;
    }

    public String getType() {
        return type;
    }

    public String getPosition() {
        return position;
    }

    public Piece toPiece() {
        return PieceFactory.of(type, Team.of(team));
    }
}
