package chess.domain.piece;

import chess.domain.Color;
import chess.practiceMove.Direction;

public final class EmptyPiece extends Piece {

    private static final String name = ".";

    public EmptyPiece() {
        super(name, Color.NONE);
    }

    @Override
    public boolean isMovableAtOnce(int abs, int abs1) {
        return false;
    }

    @Override
    public boolean isMovableDirection(Direction direction) {
        return false;
    }
}
