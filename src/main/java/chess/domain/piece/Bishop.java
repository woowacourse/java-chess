package chess.domain.piece;

import static chess.domain.piece.PieceType.BISHOP;

import chess.domain.Team;

public class Bishop extends Piece {
    public Bishop(final Team team) {
        super(team, BISHOP);
    }
}
