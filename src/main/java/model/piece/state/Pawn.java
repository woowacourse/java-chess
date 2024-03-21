package model.piece.state;

import static model.direction.MovingPattern.N;
import static model.direction.MovingPattern.S;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Position;
import model.direction.MovingPattern;
import model.piece.Color;

public final class Pawn extends Role {
    private static final List<MovingPattern> movingPatterns = List.of(N, S);
    public Pawn(Color color){
        super(color, movingPatterns);
    }

    public Set<Position> possiblePositions(Position position) {
        Set<Position> positions = new HashSet<>();
        for (MovingPattern movingPattern : movingPatterns) {
            Position movedPosition = position;
            if (movedPosition.isAvailablePosition(movingPattern)) {
                movedPosition = movedPosition.getNextPosition(movingPattern);
                positions.add(movedPosition);
            }
        }
        return positions;
    }
}
