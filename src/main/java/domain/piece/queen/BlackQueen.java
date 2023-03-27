package domain.piece.queen;

public class BlackQueen extends Queen {
    @Override
    public String getSymbol() {
        return "Q";
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
