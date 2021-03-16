package domain.piece;

import domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Division {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public boolean canMove(Position from, Position to) {
        return possiblePositions(from).contains(to);
    }

    public List<Position> possiblePositions(Position from) {
        List<Position> positions = new ArrayList<>();
        if (isBlack()) {
            positions.add(from.moveBy(0,-1));
        }
        if (isWhite()) {
            positions.add(from.moveBy(0,1));
        }
        return positions;
    }
}
