package model.piece.state;

import static model.direction.MovingPattern.NE;
import static model.direction.MovingPattern.NW;
import static model.direction.MovingPattern.SE;
import static model.direction.MovingPattern.SW;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Position;
import model.direction.MovingPattern;

public final class Bishop implements Role {
    private final List<MovingPattern> movingPatterns = List.of(NW, SW, NE, SE);

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
