package domain;

import domain.piece.Piece;

public class Queen extends Piece {

    public Queen(Side side) {
        super(side);
    }

    @Override
    protected boolean hasFollowedRule(Position source, Position target, MovePath movePath) {
        return source.isSameFile(target) || source.isSameRank(target) || source.isDiagonal(target);
    }

    @Override
    public boolean isQueen() {
        return true;
    }
}
