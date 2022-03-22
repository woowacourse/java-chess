package chess.domain.piece;

import chess.domain.piece.vo.TeamColor;

public class Bishop extends Piece {

    private static final String SYMBOL = "b";

    public Bishop(final TeamColor teamColor) {
        super(teamColor, SYMBOL);
    }
}
