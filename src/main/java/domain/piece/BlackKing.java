package domain.piece;

public class BlackKing extends Piece {
    @Override
    public String getSymbol() {
        return "K";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }
}
