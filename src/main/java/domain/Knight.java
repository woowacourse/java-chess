package domain;

public class Knight extends Piece {

    public Knight(Side side) {
        super(side);
    }

    @Override
    public boolean isKnight() {
        return true;
    }

    @Override
    public boolean canMove(Position current, Position target) {
        return (current.hasTwoFileGap(target) && current.hasOneRankGap(target)) ||
                (current.hasOneFileGap(target) && current.hasTwoRankGap(target));
    }
}
