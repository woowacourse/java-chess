package domain.piece;

public class BlackQueen extends Piece {
    @Override
    public String getSymbol() {
        return "Q";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }
}
