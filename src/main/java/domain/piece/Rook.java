package domain.piece;

import domain.board.Position;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return source.isOnSameRankAs(target)
            || source.isOnSameFileAs(target);
    }
}
