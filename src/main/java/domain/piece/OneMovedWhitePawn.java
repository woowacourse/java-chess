package domain.piece;

import domain.point.Direction;

import java.util.Map;

public class OneMovedWhitePawn extends Piece {
    @Override
    public String getSymbol() {
        return "p";
    }

    @Override
    public Map<Direction, Integer> getMovableRange() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }

    @Override
    public boolean isBlack() {
        return false;
    }

    @Override
    public boolean isWhite() {
        return true;
    }
}
