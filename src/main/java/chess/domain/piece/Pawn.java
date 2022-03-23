package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;

public class Pawn extends Piece {

    public Pawn(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }
}
