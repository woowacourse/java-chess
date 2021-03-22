package chess.domain.piece.moving;

import chess.domain.Position;
import chess.domain.piece.direction.BishopDirections;
import chess.domain.piece.direction.Directions;
import chess.exception.ImpossibleMoveException;

import java.util.ArrayList;
import java.util.List;

public class BishopMoving implements PieceMoving {

    private final Directions directions;
    private Position currentPosition;
    private List<Position> movablePositions;
    private boolean moved;

    public BishopMoving(BishopDirections bishopDirections, Position currentPosition) {
        this.directions = bishopDirections;
        this.currentPosition = currentPosition;
        this.movablePositions = new ArrayList<>();
        this.moved = false;
    }

    @Override
    public void updateMovablePositions(List<Position> existPiecePositions, List<Position> enemiesPositions) {
        movablePositions = directions.movablePositions(currentPosition, existPiecePositions, enemiesPositions);
    }

    @Override
    public void move(Position targetPosition) {
        if (movablePositions.contains(targetPosition)) {
            currentPosition = targetPosition;
            moved = true;
            return;
        }
        throw new ImpossibleMoveException();
    }

    @Override
    public Position currentPosition() {
        return currentPosition;
    }

    @Override
    public List<Position> movablePositions() {
        return movablePositions;
    }

    @Override
    public boolean notMoved() {
        return !moved;
    }

    @Override
    public boolean isSamePosition(Position position) {
        return currentPosition.equals(position);
    }

    @Override
    public int row() {
        return currentPosition.row();

    }


}
