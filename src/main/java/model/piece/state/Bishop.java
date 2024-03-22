package model.piece.state;

import static model.direction.MovingPattern.NE;
import static model.direction.MovingPattern.NW;
import static model.direction.MovingPattern.SE;
import static model.direction.MovingPattern.SW;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.direction.MovingPattern;
import model.piece.Color;
import model.position.Position;
import model.position.Route;

public final class Bishop extends Role {
    private static final List<MovingPattern> movingPatterns = List.of(NW, SW, NE, SE);

    public Bishop(Color color) {
        super(color, movingPatterns);
    }

    @Override
    public Set<Route> possibleRoutes(Position position) {
        Set<Route> possibleRoutes = new HashSet<>();
        for (MovingPattern movingPattern : movingPatterns) {
            possibleRoutes.add(findMovingPatternRoute(movingPattern, position));
        }
        return possibleRoutes;
    }
}
