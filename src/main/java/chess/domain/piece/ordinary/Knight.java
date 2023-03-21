package chess.domain.piece.ordinary;

import static chess.domain.piece.PieceType.KNIGHT;

import chess.domain.Team;

public class Knight extends OrdinaryPiece {
    public Knight(final Team team) {
        super(team, KNIGHT);
    }
}
