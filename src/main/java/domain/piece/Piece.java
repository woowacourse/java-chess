package domain.piece;

import domain.point.Direction;

import java.util.Map;

public abstract class Piece {

    public boolean isOppositeWith(Piece piece) {
        return (isWhite() && piece.isBlack()) || (isBlack() && piece.isWhite());
    }

    public abstract Map<Direction, Integer> getMovableDirectionAndRange();

    public abstract boolean isEmpty();

    public abstract boolean isWhitePawn();

    public abstract boolean isBlackPawn();

    public abstract boolean isWhite();

    public abstract boolean isBlack();

    public abstract String getSymbol();

    public abstract boolean isKing();

    public abstract float getScore();
}
