package chess.domain.piece;

import chess.domain.piece.vo.TeamColor;

public class Pawn extends Piece {

    private static final String SYMBOL = "p";

    public Pawn(final TeamColor teamColor) {
        super(teamColor, SYMBOL);
    }
}
