package chess.dto;

import chess.domain.piece.PieceType;
import chess.domain.piece.Team;

public class PieceDTO {
    private final Team team;
    private final PieceType pieceType;
    private final int row;
    private final int column;

    public PieceDTO(Team team, PieceType pieceType, int row, int colmun) {
        this.team = team;
        this.pieceType = pieceType;
        this.row = row;
        this.column = colmun;
    }

    public Team getTeam() {
        return team;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
