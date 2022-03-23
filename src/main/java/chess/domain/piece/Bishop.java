package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;

public class Bishop extends Piece {

    private static final String SYMBOL = "b";

    public Bishop(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }

    @Override
    public String getSymbol() {
        return teamColor.convertSymbolByTeamColor(SYMBOL);
    }
}
