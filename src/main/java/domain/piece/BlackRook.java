package domain.piece;

public class BlackRook extends Piece {
    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }
}
