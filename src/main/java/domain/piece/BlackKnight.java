package domain.piece;

public class BlackKnight extends Piece {
    @Override
    public String getSymbol() {
        return "N";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }
}
