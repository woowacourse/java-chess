package chess.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

public class PieceDto {
    private final String file;
    private final String rank;
    private final String type;
    private final String team;

    public PieceDto(String file, String rank, String type, String team) {
        this.file = file;
        this.rank = rank;
        this.type = type;
        this.team = team;
    }

    public Piece makePiece() {
        PieceType pieceType = PieceType.valueOf(type);
        Team team = Team.valueOf(this.team);

        return pieceType.getInstance(team);
    }

    public String getFile() {
        return file;
    }

    public String getRank() {
        return rank;
    }
}
