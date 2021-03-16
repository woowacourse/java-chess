package chess.domain.board.piece;

import chess.domain.board.Position;

public class Empty extends Piece{
    public Empty() {
        super(Owner.NONE);
    }

    @Override
    public boolean isValidMove(Position source, Position target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Score score() {
        return null;
    }
}
