package chess.piece;

import chess.ChessBoard;
import chess.position.Direction;
import chess.position.MovablePosition;
import chess.position.Position;

public class Queen extends ChessPiece {

    public Queen(Side side) {
        super(side);
    }

    @Override
    public MovablePosition findMovableRoute(ChessBoard chessBoard, Position sourcePosition) {
        MovablePosition movablePosition = new MovablePosition();
        movablePosition.findMovablePosition(chessBoard, sourcePosition, Direction.CROSS_DIRECTION, true);
        movablePosition.findMovablePosition(chessBoard, sourcePosition, Direction.DIAGONAL_DIRECTION, true);
        return movablePosition;
    }
}
