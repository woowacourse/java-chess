package chess.domain.piece.direction;

public abstract class KnightDirection extends Direction {
    protected KnightDirection(int x, int y) {
        super(x, y);
    }

    @Override
    public boolean isNorth() {
        return false;
    }

    @Override
    public boolean isSouth() {
        return false;
    }
}
