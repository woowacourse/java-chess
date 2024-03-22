package model.piece.state;

import static model.direction.MovingPattern.N;
import static model.direction.MovingPattern.NE;
import static model.direction.MovingPattern.NW;
import static model.direction.MovingPattern.S;
import static model.direction.MovingPattern.SE;
import static model.direction.MovingPattern.SW;

import java.util.ArrayList;
import java.util.List;
import model.direction.MovingPattern;
import model.piece.Color;
import model.position.Position;
import model.position.Route;

public final class Pawn extends Role {
    private static final List<MovingPattern> whiteMovingPatterns = List.of(N, NE, NW);
    private static final List<MovingPattern> blackMovingPatterns = List.of(S, SE, SW);
    private boolean isInitialMove;

    private Pawn(Color color, List<MovingPattern> movingPatterns) {
        super(color, movingPatterns);
        this.isInitialMove = true;
    }

    public static Pawn from(Color color) {
        if (color == Color.WHITE) {
            return new Pawn(color, whiteMovingPatterns);
        }
        return new Pawn(color, blackMovingPatterns);
    }

    @Override
    protected Route findMovingPatternRoute(MovingPattern movingPattern, Position movedPosition) {
        List<Position> sequentialPositions = new ArrayList<>();
        if (movedPosition.isAvailablePosition(movingPattern)) {
            movedPosition = movedPosition.getNextPosition(movingPattern);
            sequentialPositions.add(movedPosition);
        }
        if (isInitialMove && (movingPattern == N || movingPattern == S)) {
            movedPosition = movedPosition.getNextPosition(movingPattern);
            sequentialPositions.add(movedPosition);
            isInitialMove = false;
        }
        return new Route(sequentialPositions);
    }
}
