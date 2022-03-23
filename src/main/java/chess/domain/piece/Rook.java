package chess.domain.piece;

import chess.domain.board.Position;

public final class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position src, Position dest) {
        throw new UnsupportedOperationException();
    }
}
