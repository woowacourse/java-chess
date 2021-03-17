package domain.piece;

import domain.position.Position;
import domain.position.Row;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Division {
    public Pawn(Color color) {
        super(color, "p");
    }

    @Override
    public boolean canMove(Position from, Position to) {
        return possiblePositions(from).contains(to);
    }

    public List<Position> possiblePositions(Position from) {
        List<Position> positions = new ArrayList<>();
        if (isBlack()) {
            if (from.hasRow(Row.SEVEN)) {
                positions.add(from.moveBy(0, -2));
            }
            positions.add(from.moveBy(0,-1));
        }
        if (isWhite()) {
            if (from.hasRow(Row.TWO)) {
                positions.add(from.moveBy(0, 2));
            }
            positions.add(from.moveBy(0,1));
        }
        return positions;
    }
}
