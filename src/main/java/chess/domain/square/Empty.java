package chess.domain.square;

public class Empty implements Square {
    private static final Empty INSTANCE = new Empty();

    private Empty() {
    }

    public static Empty getInstance() {
        return INSTANCE;
    }
}
