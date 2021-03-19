package chess.domain.piece;

import chess.domain.board.ChessBoard;
import chess.domain.board.Direction;
import chess.domain.board.Position;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(Color color, Position position) {
        super(color, position);
        this.type = Type.KING;
    }

    @Override
    public List<Position> getMovablePositions(ChessBoard chessBoard) {
        List<Position> movablePositions = new ArrayList<>();
        List<Direction> directions = Direction.everyDirection();
        for (Direction direction : directions) {
            int xDegree = direction.getXDegree();
            int yDegree = direction.getYDegree();

            movablePositions.add(getMovablePosition(chessBoard, xDegree, yDegree));
        }
        return movablePositions;
    }

    private Position getMovablePosition(ChessBoard chessBoard, int xDegree, int yDegree) {
        if (chessBoard.hasNextPossibleSquare(position, xDegree, yDegree)) {
            Position nextPosition = new Position(position.getRow() + yDegree,
                position.getColumn() + xDegree);
            if (chessBoard.isBlank(nextPosition) || chessBoard.isAttackMove(this, nextPosition)) {
                return nextPosition;
            }
        }
        return null;
    }
}
