package chess.piece;

import chess.Color;
import chess.position.Offset;

public interface Piece {

    boolean canMove(final Offset offset, final boolean killFlag);

    Color getColor();
}
