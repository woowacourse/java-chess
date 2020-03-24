package domain.chesspiece;

public abstract class Chesspiece {
    private final String initial;

    protected Chesspiece(String initial) {
        this.initial = initial;
    }

    @Override
    public String toString() {
        return initial;
    }
}
