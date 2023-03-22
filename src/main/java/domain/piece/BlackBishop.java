package domain.piece;

public class BlackBishop extends Piece {
    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }
}
