package domain.piece;

import domain.point.Direction;

import java.util.Map;

public class BlackRook extends Piece {
    @Override
    public String getSymbol() {
        return "R";
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
    public boolean isBlack() {
        return true;
    }

    @Override
    public boolean isWhite() {
        return false;
    }
}
