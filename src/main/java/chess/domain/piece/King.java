package chess.domain.piece;

import chess.domain.ChessBoard;
import chess.domain.Direction;
import chess.domain.Position;
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

            Position nextPosition = new Position(position.getRow() + yDegree, position.getCol() + xDegree);

            if (isMovable(chessBoard, nextPosition) || isAttackMove(chessBoard, nextPosition)) {
                movablePositions.add(nextPosition);
            }
        }
        return movablePositions;
    }
}
