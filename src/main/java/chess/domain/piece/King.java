package chess.domain.piece;

import static chess.domain.piece.PieceType.KING;

import chess.domain.Team;

public class King extends Piece {

    public King(final Team team) {
        super(team, KING);
    }
}
