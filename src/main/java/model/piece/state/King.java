package model.piece.state;

import static model.direction.MovingPattern.E;
import static model.direction.MovingPattern.N;
import static model.direction.MovingPattern.NE;
import static model.direction.MovingPattern.NW;
import static model.direction.MovingPattern.S;
import static model.direction.MovingPattern.SE;
import static model.direction.MovingPattern.SW;
import static model.direction.MovingPattern.W;

import java.util.ArrayList;
import java.util.List;
import model.direction.MovingPattern;
import model.piece.Color;
import model.position.Position;
import model.position.Route;

public final class King extends Role {
    private static final List<MovingPattern> movingPatterns = List.of(N, NE, E, SE, S, SW, W, NW);

    private King(Color color) {
        super(color, movingPatterns);
    }

    public static King from(Color color) {
        return new King(color);
    }

    @Override
    protected Route findMovingPatternRoute(MovingPattern movingPattern, Position movedPosition) {
        List<Position> sequentialPositions = new ArrayList<>();
        if (movedPosition.isAvailablePosition(movingPattern)) {
            movedPosition = movedPosition.getNextPosition(movingPattern);
            sequentialPositions.add(movedPosition);
        }
        return new Route(sequentialPositions);
    }
}
