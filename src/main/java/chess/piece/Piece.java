package chess.piece;

import chess.board.Variation;

public interface Piece {
    boolean canMove(Variation variation);

    boolean isSameTeam(Team team);
}
