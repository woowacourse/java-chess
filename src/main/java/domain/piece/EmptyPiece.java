package domain.piece;

import domain.board.Board;
import domain.position.Position;

public class EmptyPiece extends Piece {

    private static final String NAME = ".";

    public EmptyPiece() {
        super(NAME);
    }

    @Override
    public boolean canMove(Board board, Position source, Position target) {
        return false;
    }

    @Override
    public boolean isNotEmpty() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
