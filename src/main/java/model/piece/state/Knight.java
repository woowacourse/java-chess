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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.position.Position;
import model.position.Route;
import model.direction.MovingPattern;
import model.piece.Color;

public final class Knight extends Role {
    private static final List<MovingPattern> movingPatterns = List.of(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    public Knight(Color color){
        super(color, movingPatterns);
    }

    @Override
    public Set<Route> possibleRoutes(Position position) {
        Set<Route> possibleRoutes = new HashSet<>();
        for (MovingPattern movingPattern : movingPatterns) {
            possibleRoutes.add(getRoute(movingPattern, position));
        }
        return possibleRoutes;
    }

    @Override
    protected Route getRoute(MovingPattern movingPattern, Position movedPosition) {
        List<Position> sequentialPositions = new ArrayList<>();
        if (movedPosition.isAvailablePosition(movingPattern)) {
            movedPosition = movedPosition.getNextPosition(movingPattern);
            sequentialPositions.add(movedPosition);
        }
        return new Route(sequentialPositions);
    }
}
