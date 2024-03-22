package model.piece.state;

import static model.direction.MovingPattern.EEN;
import static model.direction.MovingPattern.EES;
import static model.direction.MovingPattern.NNE;
import static model.direction.MovingPattern.NNW;
import static model.direction.MovingPattern.SSE;
import static model.direction.MovingPattern.SSW;
import static model.direction.MovingPattern.WWN;
import static model.direction.MovingPattern.WWS;

import java.util.ArrayList;
import java.util.List;
import model.direction.MovingPattern;
import model.piece.Color;
import model.position.Position;
import model.position.Route;

public final class Knight extends Role {
    private static final List<MovingPattern> movingPatterns = List.of(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);

    private Knight(Color color) {
        super(color, movingPatterns);
    }

    public static Knight from(Color color) {
        return new Knight(color);
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
