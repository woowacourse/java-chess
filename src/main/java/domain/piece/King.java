package domain.piece;

import domain.position.Position;

public class King extends Piece {

    public King(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return source.isLegalRankStep(target, 0, 1) && source.isLegalFileStep(target, 0, 1);
    }

    @Override
    public String display() {
        return "K";
    }
}
