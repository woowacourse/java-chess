package domain.piece;

import domain.point.Direction;

import java.util.Map;

public class BlackBishop extends Piece {
    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }

    @Override
    public Map<Direction, Integer> getMovableRange() {
        return null;
    }

    @Override
    public boolean isWhite() {
        return false;
    }

    @Override
    public boolean isBlack() {
        return true;
    }
}
