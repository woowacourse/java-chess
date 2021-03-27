package chess.domain.piece.direction;

public class Southwest extends Direction {
    public Southwest() {
        super(-1, -1);
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
