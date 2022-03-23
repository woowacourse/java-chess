package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;

public class King extends Piece {

    public King(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }
}
