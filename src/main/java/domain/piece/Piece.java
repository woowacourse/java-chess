package domain.piece;

import domain.point.Direction;

import java.util.Map;

public abstract class Piece {
    public boolean isEmpty() {
        return this.getClass() == Empty.class;
    }

    public boolean isWhitePawn() {
        return this.getClass() == WhitePawn.class;
    }

    public boolean isBlackPawn() {
        return this.getClass() == BlackPawn.class;
    }

    public boolean isOppositeWith(Piece piece) {
        return (isWhite() && piece.isBlack()) || (isBlack() && piece.isWhite());
    }

    public abstract Map<Direction, Integer> getMovableRange();

    public abstract boolean isWhite();

    public abstract boolean isBlack();

    public abstract String getSymbol();
}
