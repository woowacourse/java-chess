package model.piece.state;

import static model.direction.MovingPattern.N;
import static model.direction.MovingPattern.S;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import model.position.Position;
import model.position.Route;
import model.direction.MovingPattern;
import model.piece.Color;

public final class Pawn extends Role {
    private static final List<MovingPattern> movingPatterns = List.of(N, S);
    private boolean isInitialMove;

    public Pawn(Color color) {
        super(color, movingPatterns);
        this.isInitialMove = true;
    }

    @Override
    public Set<Route> possibleRoutes(Position position) {
        if (color == Color.WHITE) {
            return possiblePawnMovingPostions(N, position);
        }
        return possiblePawnMovingPostions(S, position);
    }

    private Set<Route> possiblePawnMovingPostions(MovingPattern movingPattern, Position position) {
        List<Position> positions = new ArrayList<>();
        Position nextPosition = position.getNextPosition(movingPattern);
        positions.add(nextPosition);
        if (isInitialMove) {
            Position doubleMovePosition = nextPosition.getNextPosition(movingPattern);
            positions.add(doubleMovePosition);
        }
        return Set.of(new Route(positions));
    }
}
