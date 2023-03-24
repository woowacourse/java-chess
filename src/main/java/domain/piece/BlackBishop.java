package domain.piece;

public class BlackBishop extends Bishop {
    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }

    @Override
    public boolean isWhite() {
        return false;
    }

    @Override
    public boolean isBlack() {
        return true;
    }
}
