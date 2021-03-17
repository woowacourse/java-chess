package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Direction;
import chess.domain.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Queen extends Piece {

    public Queen(Color color, Position position) {
        super(color, position);
        this.type = Type.QUEEN;
    }

    @Override
    public List<Position> getMovablePositions(ChessBoard chessBoard) {
        List<Position> movablePositions = new ArrayList<>();
        List<Direction> directions = Direction.everyDirection();
        for (Direction direction : directions) {
            int xDegree = direction.getXDegree();
            int yDegree = direction.getYDegree();

            movablePositions.addAll(getMovablePosition(chessBoard, xDegree, yDegree));
        }
        return movablePositions;
    }

    private List<Position> getMovablePosition(ChessBoard chessBoard, int xDegree, int yDegree) {
        List<Position> movablePositions = new ArrayList<>();
        Position currentPosition = position;
        Position nextPosition = new Position(currentPosition.getRow() + yDegree,
            currentPosition.getCol() + xDegree);
        while (isInBound(nextPosition)) {
            if (!chessBoard.isBlank(nextPosition)) {
                if (chessBoard.isAttackMove(this, nextPosition)) {
                    movablePositions.add(nextPosition);
                }
                break;
            }
            movablePositions.add(nextPosition);
            nextPosition = new Position(nextPosition.getRow() + yDegree,
                nextPosition.getCol() + xDegree);
        }
        return Collections.unmodifiableList(movablePositions);
    }
}
