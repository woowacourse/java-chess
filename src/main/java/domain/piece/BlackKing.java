package domain.piece;

public class BlackKing extends King {
    @Override
    public String getSymbol() {
        return "K";
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
