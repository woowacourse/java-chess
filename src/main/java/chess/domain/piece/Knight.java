package chess.domain.piece;

import chess.domain.piece.vo.TeamColor;

public class Knight extends Piece {

    private static final String SYMBOL = "n";

    public Knight(final TeamColor teamColor) {
        super(teamColor, SYMBOL);
    }
}
