package chess.dto.board;

import chess.domain.board.Cell;
import chess.domain.piece.Piece;
import chess.domain.piece.TeamType;

public class PieceDTO {

    private final String name;
    private final TeamType teamType;

    private PieceDTO(String name, TeamType teamType) {
        this.name = name;
        this.teamType = teamType;
    }

    public static PieceDTO from(Cell cell) {
        if (cell.isEmpty()) {
            return null;
        }
        Piece piece = cell.getPiece();
        return new PieceDTO(piece.getName(), piece.getTeamType());
    }

    public String getName() {
        return name;
    }

    public TeamType getTeamType() {
        return teamType;
    }
}
