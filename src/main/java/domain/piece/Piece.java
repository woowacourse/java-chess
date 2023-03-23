package domain.piece;

public abstract class Piece {
    public abstract String getSymbol();

    public boolean isEmpty() {
        return this.getClass() == Empty.class;
    }
}
