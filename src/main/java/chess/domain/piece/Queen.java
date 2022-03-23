package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;

public class Queen extends Piece {

    public Queen(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }
}
