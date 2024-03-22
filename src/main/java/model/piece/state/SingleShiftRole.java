package model.piece.state;

import java.util.ArrayList;
import java.util.List;
import model.direction.Direction;
import model.direction.ShiftPattern;
import model.piece.Color;
import model.position.Position;
import model.position.Route;

public abstract class SingleShiftRole extends Role {
    protected SingleShiftRole(Color color, ShiftPattern shiftPattern) {
        super(color, shiftPattern);
    }

    @Override
    protected Route findRouteByDirection(Direction direction, Position source) {
        List<Position> sequentialPositions = new ArrayList<>();
        if (source.isAvailablePosition(direction)) {
            source = source.getNextPosition(direction);
            sequentialPositions.add(source);
        }
        return new Route(direction, sequentialPositions);
    }
}
