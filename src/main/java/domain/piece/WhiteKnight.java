package domain.piece;

public class WhiteKnight extends Piece {
    @Override
    public String getSymbol() {
        return "n";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }
}
