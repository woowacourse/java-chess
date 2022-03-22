package chess.domain.piece;

import chess.domain.piece.vo.TeamColor;

public class Rook extends Piece {

    private static final String SYMBOL = "r";

    public Rook(final TeamColor teamColor) {
        super(teamColor, SYMBOL);
    }
}
