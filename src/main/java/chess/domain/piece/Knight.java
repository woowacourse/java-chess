package chess.domain.piece;

import static chess.domain.piece.PieceType.KNIGHT;

import chess.domain.Team;

public class Knight extends Piece {
    public Knight(final Team team) {
        super(team, KNIGHT);
    }
}
