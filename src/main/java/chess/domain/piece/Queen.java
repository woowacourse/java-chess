package chess.domain.piece;

import static chess.domain.piece.PieceType.QUEEN;

import chess.domain.Team;

public class Queen extends Piece {
    public Queen(final Team team) {
        super(team, QUEEN);
    }
}
