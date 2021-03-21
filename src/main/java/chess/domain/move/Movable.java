package chess.domain.move;

import chess.domain.piece.Pieces;
import chess.domain.position.Target;

public interface Movable {
    void move(final Target target, final Pieces basePieces, final Pieces targetPieces);
}
