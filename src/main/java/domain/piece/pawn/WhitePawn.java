package domain.piece.pawn;

import domain.piece.Piece;
import domain.point.Direction;

import java.util.HashMap;
import java.util.Map;

public class WhitePawn extends Piece {
    @Override
    public String getSymbol() {
        return "p";
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }

    @Override
    public Map<Direction, Integer> getMovableDirectionAndRange() {
        Map<Direction, Integer> movableRange = new HashMap<>();
        movableRange.put(Direction.UP, 2);
        return movableRange;
    }

    @Override
    public boolean isBlack() {
        return false;
    }

    @Override
    public boolean isWhite() {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isBlackPawn() {
        return false;
    }

    @Override
    public boolean isWhitePawn() {
        return true;
    }
}
