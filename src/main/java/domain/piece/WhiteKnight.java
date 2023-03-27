package domain.piece;

public class WhiteKnight extends Knight {
    @Override
    public String getSymbol() {
        return "n";
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
