package chess.domain.piece;

import chess.domain.board.Direction;
import chess.domain.board.Position;

public final class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position src, Position dest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Direction findDirection(Position src, Position dest) {
        throw new UnsupportedOperationException();
    }
}
