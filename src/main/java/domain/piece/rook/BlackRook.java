package domain.piece.rook;

public class BlackRook extends Rook {
    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }

    @Override
    public boolean isBlack() {
        return true;
    }

    @Override
    public boolean isWhite() {
        return false;
    }
}
