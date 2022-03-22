package chess.domain.piece;

import chess.domain.piece.vo.TeamColor;

public abstract class Piece {

    private final TeamColor teamColor;
    private final String symbol;

    Piece(final TeamColor teamColor, final String symbol) {
        this.teamColor = teamColor;
        this.symbol = symbol;
    }

    public final String getSymbol() {
        return teamColor.convertSymbolByTeamColor(symbol);
    }
}
