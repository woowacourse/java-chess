package domain.piece;

import domain.position.Position;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return source.isStraight(target);
    }
}
