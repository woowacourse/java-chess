package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;

public class Knight extends Piece {

    public Knight(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }
}
