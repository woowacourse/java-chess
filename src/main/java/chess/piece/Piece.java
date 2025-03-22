package chess.piece;

import chess.position.Movement;
import chess.position.Offset;

public interface Piece {

    boolean canMove(final Offset offset);
}
