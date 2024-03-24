package domain.piece;

import domain.board.Position;

public class Empty extends Piece {

    public Empty(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return false;
    }

    @Override
    public String asString() {
        return ".";
    }
}
