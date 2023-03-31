package chess.piece;

import chess.ChessBoard;
import chess.position.Direction;
import chess.position.MovablePosition;
import chess.position.Position;

public class Rook extends ChessPiece {

    public Rook(Side side) {
        super(side);
    }

    @Override
    public MovablePosition findMovableRoute(ChessBoard chessBoard, Position sourcePosition) {
        MovablePosition movablePosition = new MovablePosition();
        movablePosition.findMovablePosition(chessBoard, sourcePosition, Direction.CROSS_DIRECTION, true);
        return movablePosition;
    }
}
