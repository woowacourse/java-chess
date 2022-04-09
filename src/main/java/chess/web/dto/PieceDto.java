package chess.web.dto;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;

public class PieceDto {
    private final String team;
    private final String symbol;

    public PieceDto(Piece piece) {
        Team team = piece.getTeam();
        this.team = team.getTeam();
        this.symbol = piece.getSymbolByTeam();
    }

    public String getTeam() {
        return team;
    }

    public String getSymbol() {
        return symbol.toLowerCase();
    }
}
