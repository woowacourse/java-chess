package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;

public abstract class SlidingPiece extends Piece {

    protected SlidingPiece(final Color color, final Position position) {
        super(color, position);
    }
}
