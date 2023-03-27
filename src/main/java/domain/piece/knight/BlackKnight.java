package domain.piece.knight;

public class BlackKnight extends Knight {
    @Override
    public String getSymbol() {
        return "N";
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
