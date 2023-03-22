package domain.piece;

public class WhiteRook extends Piece {
    @Override
    public String getSymbol() {
        return "r";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }
}
