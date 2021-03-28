package chess.domain.piece.direction;

public class Southeast extends Direction {
    public Southeast() {
        super(1, -1);
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
