package chess.piece;

import chess.position.Movement;

public interface Piece {

    boolean canMove(final Movement movement);
}
