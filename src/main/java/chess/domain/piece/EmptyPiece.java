package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;

public final class EmptyPiece extends Piece {

    private static final String name = ".";

    public EmptyPiece() {
        super(name, Color.NONE);
    }

    @Override
    public boolean isMovable(Position start, Position end, Color colorOfDestination) {
        return false;
    }
}
