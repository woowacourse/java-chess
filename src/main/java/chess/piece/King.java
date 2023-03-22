package chess.piece;

import chess.ChessBoard;
import chess.position.Direction;
import chess.position.MovablePosition;
import chess.position.Position;

public class King extends ChessPiece {
    public King(Shape shape, Side side) {
        super(shape, side);
    }

    @Override
    public MovablePosition findMovableRoute(ChessBoard chessBoard, Position sourcePosition) {
        MovablePosition movablePosition = new MovablePosition();
        movablePosition.findMovablePosition(chessBoard, sourcePosition, Direction.CROSS_DIRECTION, false);
        movablePosition.findMovablePosition(chessBoard, sourcePosition, Direction.DIAGONAL_DIRECTION, false);
        return movablePosition;
    }
}
