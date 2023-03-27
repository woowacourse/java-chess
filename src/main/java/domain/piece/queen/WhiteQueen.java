package domain.piece.queen;

public class WhiteQueen extends Queen {
    @Override
    public String getSymbol() {
        return "q";
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
