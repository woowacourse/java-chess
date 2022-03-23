package chess.domain.piece;

import chess.domain.board.Position;

public final class Knight extends Piece {

    public Knight(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position src, Position dest) {
        throw new UnsupportedOperationException();
    }
}
