package model.piece.role;

import java.util.ArrayList;
import java.util.List;
import model.direction.Direction;
import model.direction.ShiftPattern;
import model.piece.Color;
import model.position.Position;
import model.position.Route;

public abstract class MultiShiftRole extends Role {
    protected MultiShiftRole(Color color, ShiftPattern shiftPattern) {
        super(color, shiftPattern);
    }

    @Override
    protected Route findRouteByDirection(Direction direction, Position source) {
        List<Position> sequentialPositions = new ArrayList<>();
        while (source.hasAvailableNextPostitionToDirection(direction)) {
            source = source.getNextPosition(direction);
            sequentialPositions.add(source);
        }
        return new Route(direction, sequentialPositions);
    }
}
