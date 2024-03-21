package model.piece.state;


import static model.direction.MovingPattern.E;
import static model.direction.MovingPattern.N;
import static model.direction.MovingPattern.S;
import static model.direction.MovingPattern.W;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.direction.MovingPattern;
import model.piece.Color;
import model.position.Position;
import model.position.Route;

public final class Rook extends Role {
    private static final List<MovingPattern> movingPatterns = List.of(W, E, N, S);

    public Rook(Color color) {
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
}
