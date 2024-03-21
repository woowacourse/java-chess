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
    private boolean isInitialMove;

    public Pawn(Color color) {
        super(color, movingPatterns);
        this.isInitialMove = true;
    }

    @Override
    public Set<Position> possiblePositions(Position position) {
        if(color == Color.WHITE){
            return possiblePawnMovingPostions(N, position);
        }
        return possiblePawnMovingPostions(S, position);
    }

    private Set<Position> possiblePawnMovingPostions(MovingPattern movingPattern, Position position){
        Set<Position> positions = new HashSet<>();
        Position nextPosition = position.getNextPosition(movingPattern);
        positions.add(nextPosition);
        if (isInitialMove) {
            Position doubleMovePosition = nextPosition.getNextPosition(movingPattern);
            positions.add(doubleMovePosition);
        }
        return positions;
    }
}
