package domain.chessboard;

public class Empty implements SquareStatus {

    private final EmptyType emptyType;

    public Empty(final EmptyType emptyType) {
        this.emptyType = emptyType;
    }

    @Override
    public Type getType() {
        return emptyType;
    }
}
