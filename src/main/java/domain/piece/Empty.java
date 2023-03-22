package domain.piece;

public class Empty extends Piece {
    @Override
    public String getSymbol() {
        return ".";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }
}
