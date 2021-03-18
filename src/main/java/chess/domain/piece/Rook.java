package chess.domain.piece;

import chess.domain.ChessBoard;
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
    public List<Position> getMovablePositions(ChessBoard chessBoard) {
        List<Position> movablePositions = new ArrayList<>();
        List<Direction> directions = Direction.linearDirection();
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
        while (chessBoard.hasNextPossibleSquare(currentPosition, xDegree, yDegree)) {
            Position nextPosition = new Position(currentPosition.getRow() + yDegree,
                currentPosition.getColumn() + xDegree);
            if (chessBoard.isBlank(nextPosition)) {
                movablePositions.add(nextPosition);
            }
            if (chessBoard.isAttackMove(this, nextPosition)) {
                movablePositions.add(nextPosition);
                break;
            }
            currentPosition = nextPosition;
        }
        return Collections.unmodifiableList(movablePositions);
    }
}
