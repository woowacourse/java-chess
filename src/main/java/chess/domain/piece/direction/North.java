package chess.domain.piece.direction;

public class North extends Direction {
    public North() {
        super(0, 1);
    }

    @Override
    public boolean isNorth() {
        return true;
    }

    @Override
    public boolean isSouth() {
        return false;
    }
}
