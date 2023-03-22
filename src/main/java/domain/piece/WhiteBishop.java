package domain.piece;

public class WhiteBishop extends Piece {
    @Override
    public String getSymbol() {
        return "b";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }
}
