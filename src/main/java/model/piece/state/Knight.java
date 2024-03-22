package model.piece.state;

import static model.direction.Direction.EEN;
import static model.direction.Direction.EES;
import static model.direction.Direction.NNE;
import static model.direction.Direction.NNW;
import static model.direction.Direction.SSE;
import static model.direction.Direction.SSW;
import static model.direction.Direction.WWN;
import static model.direction.Direction.WWS;

import java.util.ArrayList;
import java.util.List;
import model.direction.Direction;
import model.piece.Color;
import model.position.Position;
import model.position.Route;

public final class Knight extends Role {
    private static final List<Direction> DIRECTIONS = List.of(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);

    private Knight(Color color) {
        super(color, DIRECTIONS);
    }

    public static Knight from(Color color) {
        return new Knight(color);
    }

    @Override
    protected Route findMovingPatternRoute(Direction direction, Position movedPosition) {
        List<Position> sequentialPositions = new ArrayList<>();
        if (movedPosition.isAvailablePosition(direction)) {
            movedPosition = movedPosition.getNextPosition(direction);
            sequentialPositions.add(movedPosition);
        }
        return new Route(sequentialPositions);
    }
}
