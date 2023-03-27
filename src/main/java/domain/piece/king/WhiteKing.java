package domain.piece.king;

public class WhiteKing extends King {
    @Override
    public String getSymbol() {
        return "k";
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
