package chess.domain.piece.direction;

public class West extends Direction {
    public West() {
        super(-1, 0);
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