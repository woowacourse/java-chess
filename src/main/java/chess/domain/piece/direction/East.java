package chess.domain.piece.direction;

public class East extends Direction {
    public East() {
        super(1, 0);
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
