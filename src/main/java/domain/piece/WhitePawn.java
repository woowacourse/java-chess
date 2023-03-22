package domain.piece;

public class WhitePawn extends Piece {
    @Override
    public String getSymbol() {
        return "p";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }
}
