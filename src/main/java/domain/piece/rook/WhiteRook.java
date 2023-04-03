package domain.piece.rook;

public class WhiteRook extends Rook {
    @Override
    public String getSymbol() {
        return "r";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }

    @Override
    public boolean isBlack() {
        return false;
    }

    @Override
    public boolean isWhite() {
        return true;
    }
}
