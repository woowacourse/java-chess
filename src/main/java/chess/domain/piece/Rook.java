package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rook extends Piece {

    public Rook(Color color, Position position) {
        super(color, position);
        this.type = Type.ROOK;
    }

    @Override
    public List<Position> getMovablePositions(List<Position> existingPositions) {
        List<Position> movablePositions = new ArrayList<>();
        List<Direction> directions = Direction.linearDirection();
        for (Direction direction : directions) {
            int xDegree = direction.getXDegree();
            int yDegree = direction.getYDegree();

            movablePositions.addAll(getMovablePosition(existingPositions, xDegree, yDegree));
        }
        return Collections.unmodifiableList(movablePositions);
    }

    private List<Position> getMovablePosition(List<Position> existingPositions, int xDegree, int yDegree) {
        List<Position> movablePositions = new ArrayList<>();
        Position currentPosition = position;
        while (hasNext(currentPosition)) {
            Position nextPosition = new Position(currentPosition.getRow() + yDegree, currentPosition.getCol() + xDegree);
            if (existingPositions.contains(nextPosition)) {
                movablePositions.add(nextPosition);
                break;
            }
            movablePositions.add(nextPosition);
            currentPosition = nextPosition;
        }
        return Collections.unmodifiableList(movablePositions);
    }
}
