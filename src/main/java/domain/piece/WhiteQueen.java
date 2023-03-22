package domain.piece;

public class WhiteQueen extends Piece {
    @Override
    public String getSymbol() {
        return "q";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }
}
