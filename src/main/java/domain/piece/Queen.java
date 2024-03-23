package domain.piece;

import domain.board.Position;

public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public boolean canMove(Position source, Position target) {
        return source.isOnSameDiagonalAs(target)
                || source.isOnSameRankAs(target)
                || source.isOnSameFileAs(target);
    }
}
