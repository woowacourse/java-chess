package chess.domain.piece;

import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;

public class Pawn extends AbstractPawn {

    public Pawn(final Color color, final Position position) {
        super(color, position);
    }

    @Override
    public Piece move(final Position source, final Position target) {
        return null;
    }

}
