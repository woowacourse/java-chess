package domain.piece;

import domain.Location;
import domain.type.Color;
import domain.type.PieceType;

public final class EmptyPiece extends Piece {
    
    protected EmptyPiece() {
        super(Color.NONE, PieceType.EMPTY);
    }

    @Override
    protected boolean isNotMovable(Location start, Location end) {
        return false;
    }
}
