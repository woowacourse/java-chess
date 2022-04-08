package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Team;
import chess.domain.position.Position;

public class PieceDto {
    private final String team;
    private final String type;
    private final String position;

    public PieceDto(Piece piece, Position position) {
        this(piece.getTeam().name().toLowerCase(),
                piece.getInfo().getType(),
                position.getName());
    }

    public PieceDto(String team, String type, String position) {
        this.team = team;
        this.type = type;
        this.position = position;
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
