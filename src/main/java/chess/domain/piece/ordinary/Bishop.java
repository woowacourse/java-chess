package chess.domain.piece.ordinary;

import chess.domain.Team;

import static chess.domain.piece.PieceType.BISHOP;

public class Bishop extends OrdinaryPiece {
    public Bishop(final Team team) {
        super(team, BISHOP);
    }
}
