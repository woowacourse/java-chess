package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;

public class Bishop extends Piece {

    public Bishop(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }
}
