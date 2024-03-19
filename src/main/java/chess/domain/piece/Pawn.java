package chess.domain.piece;

import chess.domain.attribute.Color;
import chess.domain.attribute.Position;

public class Pawn extends AbstractPawn {

    protected Pawn(final Color color, final Position position) {
        super(color, position);
    }
}
