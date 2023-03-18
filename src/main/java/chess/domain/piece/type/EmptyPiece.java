package chess.domain.piece.type;

import chess.domain.board.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

public final class EmptyPiece extends Piece {

    private static final String name = ".";

    public EmptyPiece() {
        super(name, Color.NONE);
    }

    @Override
    public boolean isMovable(Position start, Position end, Color colorOfDestination) {
        return false;
    }

    @Override
    protected void checkMovableDirection(Direction direction) {
        // EmptyPiece doesn't need to move
    }
}
