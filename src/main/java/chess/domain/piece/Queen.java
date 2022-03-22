package chess.domain.piece;

import chess.domain.piece.vo.TeamColor;

public class Queen extends Piece {

    private static final String SYMBOL = "q";

    public Queen(final TeamColor teamColor) {
        super(teamColor, SYMBOL);
    }
}
