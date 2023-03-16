package chess.domain.piece;

import static chess.domain.piece.PieceType.PAWN;

import chess.domain.Team;

public class Pawn extends Piece {
    public Pawn(final Team team) {
        super(team, PAWN);
    }
}
