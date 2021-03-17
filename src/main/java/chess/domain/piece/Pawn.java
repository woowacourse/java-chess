package chess.domain.piece;

import chess.domain.Direction;
import chess.domain.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece{

    public Pawn(Color color, Position position) {
        super(color, position);
        this.type = Type.PAWN;
    }

    @Override
    public List<Position> getMovablePositions(List<Position> existingPositions) {
        List<Position> movablePositions = new ArrayList<>();
        List<Direction> directions = getDirections();
        for (Direction direction : directions) {
            int xDegree = direction.getXDegree();
            int yDegree = direction.getYDegree();

            movablePositions.add(new Position(position.getRow() + yDegree, position.getCol() + xDegree));
        }
        return movablePositions;
    }

    private List<Direction> getDirections() {
        if (this.color.equals(Color.BLACK)) {
            return Direction.blackPawnDirection();
        }
        return Direction.whitePawnDirection();
    }
}
