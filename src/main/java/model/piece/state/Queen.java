package model.piece.state;

import static model.direction.MovingPattern.E;
import static model.direction.MovingPattern.N;
import static model.direction.MovingPattern.NE;
import static model.direction.MovingPattern.NW;
import static model.direction.MovingPattern.S;
import static model.direction.MovingPattern.SE;
import static model.direction.MovingPattern.SW;
import static model.direction.MovingPattern.W;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Position;
import model.direction.MovingPattern;
import model.piece.Color;

public final class Queen extends Role {
    private static final List<MovingPattern> movingPatterns = List.of(W, E, S, N, NW, SW, NE, SE);
    public Queen(Color color){
        super(color, movingPatterns);
    }

    @Override
    public Set<Position> possiblePositions(Position position) {
        Set<Position> positions = new HashSet<>();
        for (MovingPattern movingPattern : movingPatterns) {
            Position movedPosition = position;
            while (movedPosition.isAvailablePosition(movingPattern)) {
                movedPosition = movedPosition.getNextPosition(movingPattern);
                positions.add(movedPosition);
            }
        }
        return positions;
    }
}
