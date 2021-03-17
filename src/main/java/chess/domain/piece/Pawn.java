package chess.domain.piece;

import chess.domain.ChessBoard;
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
    public List<Position> getMovablePositions(ChessBoard chessBoard) {
        List<Position> movablePositions = new ArrayList<>();
        List<Direction> directions = getDirections();
        for (Direction direction : directions) {
            int xDegree = direction.getXDegree();
            int yDegree = direction.getYDegree();

            movablePositions.addAll(getMovablePosition(chessBoard, xDegree, yDegree));
        }
        return movablePositions;
    }

    private List<Position> getMovablePosition(ChessBoard chessBoard, int xDegree, int yDegree) {
        List<Position> movablePositions = new ArrayList<>();
        Position nextPosition = new Position(position.getRow() + yDegree, position.getCol() + xDegree);
        if (xDegree == 0) {
            if (isInBound(nextPosition)) {
                movablePositions.add(nextPosition);
            }
            if (isStartingPosition()) {
                nextPosition = new Position(nextPosition.getRow() + yDegree,
                    nextPosition.getCol() + xDegree);
                movablePositions.add(nextPosition);
            }
        }
        if (isInBound(nextPosition)) {
            if (chessBoard.isAttackMove(this, nextPosition)) {
                movablePositions.add(nextPosition);
            }
        }
        return Collections.unmodifiableList(movablePositions);
    }

    private List<Direction> getDirections() {
        if (this.color.equals(Color.BLACK)) {
            return Direction.blackPawnDirection();
        }
        return Direction.whitePawnDirection();
    }

    private boolean isStartingPosition() {
        if (this.color.equals(Color.BLACK)) {
            return this.position.getRow() == 1;
        }
        return this.position.getRow() == 6;
    }
}
