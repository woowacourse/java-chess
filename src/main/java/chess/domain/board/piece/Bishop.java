package chess.domain.board.piece;

import chess.domain.board.Position;

public class Bishop extends Piece {
    public Bishop(Owner owner) {
        super(owner);
    }

    @Override
    public boolean isValidMove(Position source, Position target) {
        return false;
    }

    @Override
    public Score score() {
        return null;
    }
}
