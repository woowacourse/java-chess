package model.piece.state;

import static model.direction.MovingPattern.EEN;
import static model.direction.MovingPattern.EES;
import static model.direction.MovingPattern.NNE;
import static model.direction.MovingPattern.NNW;
import static model.direction.MovingPattern.SSE;
import static model.direction.MovingPattern.SSW;
import static model.direction.MovingPattern.WWN;
import static model.direction.MovingPattern.WWS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import model.Position;
import model.direction.MovingPattern;
import model.piece.Color;

public final class Knight extends Role {
    private static final List<MovingPattern> movingPatterns = List.of(NNE, NNW, SSE, SSW, EEN, EES, WWN, WWS);
    public Knight(Color color){
        super(color, movingPatterns);
    }

    @Override
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
