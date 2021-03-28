package chess.domain.piece.direction;

public class South extends Direction {
    public South() {
        super(0, -1);
    }

    @Override
    public boolean isNorth() {
        return false;
    }

    @Override
    public boolean isSouth() {
        return true;
    }
}
