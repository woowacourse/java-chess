package chess.domain.piece.direction;

public class Northwest extends Direction {
    public Northwest() {
        super(-1, 1);
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
