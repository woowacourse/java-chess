package domain.piece;

public class WhiteKing extends Piece {
    @Override
    public String getSymbol() {
        return "k";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }
}
