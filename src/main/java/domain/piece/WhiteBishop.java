package domain.piece;

public class WhiteBishop extends Bishop {
    @Override
    public String getSymbol() {
        return "b";
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
