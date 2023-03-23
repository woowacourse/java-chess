package chess.domain.piece.ordinary;

import static chess.domain.piece.PieceType.KING;

import chess.domain.Team;

public class King extends OrdinaryPiece {

    public King(final Team team) {
        super(team, KING);
    }
}
