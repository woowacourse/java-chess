package domain.piece;

import domain.point.Direction;

import java.util.Map;

public class Empty extends Piece {

    public static final int SCORE = 0;

    @Override
    public String getSymbol() {
        return ".";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }

    @Override
    public Map<Direction, Integer> getMovableDirectionAndRange() {
        return null;
    }

    @Override
    public boolean isBlack() {
        return false;
    }

    @Override
    public boolean isWhite() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isWhitePawn() {
        return false;
    }

    @Override
    public boolean isBlackPawn() {
        return false;
    }

    @Override
    public float getScore() {
        return SCORE;
    }
}
