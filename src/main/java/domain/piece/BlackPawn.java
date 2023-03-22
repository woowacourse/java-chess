package domain.piece;

public class BlackPawn extends Piece {
    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }
}
