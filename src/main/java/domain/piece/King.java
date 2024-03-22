package domain.piece;

import domain.position.Position;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return source.isNeighbor(target);
    }

    @Override
    public String display() {
        return "K";
    }
}
