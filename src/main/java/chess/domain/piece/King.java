package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;

public class King extends Piece {

    private static final String SYMBOL = "k";

    public King(final TeamColor teamColor) {
        super(teamColor, SYMBOL);
    }
}
