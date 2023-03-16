package chess.domain.piece;

import static chess.domain.piece.PieceType.ROOK;

import chess.domain.Team;

public class Rook extends Piece {
    public Rook(final Team team) {
        super(team, ROOK);
    }

}
