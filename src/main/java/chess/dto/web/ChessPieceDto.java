package chess.dto.web;

import chess.domain.Team;
import chess.domain.piece.ChessPiece;
import chess.dto.ChessPieceSymbol;

public class ChessPieceDto {
    private final ChessPieceSymbol name;
    private final Team team;

    private ChessPieceDto(ChessPieceSymbol name, Team team) {
        this.name = name;
        this.team = team;
    }

    public static ChessPieceDto of(ChessPiece chessPiece) {
        return new ChessPieceDto(ChessPieceSymbol.of(chessPiece), chessPiece.getTeam());
    }

    public ChessPieceSymbol getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }
}
