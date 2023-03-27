package domain.piece.pawn;

import domain.piece.Piece;
import domain.point.Direction;

import java.util.HashMap;
import java.util.Map;

public class OnceMovedBlackPawn extends Piece {
    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public Map<Direction, Integer> getMovableRange() {
        Map<Direction, Integer> movableRange = new HashMap<>();
        movableRange.put(Direction.UP, 1);
        return movableRange;
    }

    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
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
