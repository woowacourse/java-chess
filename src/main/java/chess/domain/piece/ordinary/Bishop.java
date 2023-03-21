package chess.domain.piece.ordinary;

import static chess.domain.piece.PieceType.BISHOP;

import chess.domain.Team;
import chess.domain.piece.ordinary.OrdinaryPiece;

public class Bishop extends OrdinaryPiece {
    public Bishop(final Team team) {
        super(team, BISHOP);
    }
}
