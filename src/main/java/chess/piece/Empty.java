package chess.piece;

import chess.ChessBoard;
import chess.position.MovablePosition;
import chess.position.Position;

public class Empty extends ChessPiece {

    public Empty(Shape shape, Side side) {
        super(shape, side);
    }

    @Override
    public MovablePosition findMovableRoute(ChessBoard chessBoard, Position sourcePosition) {
        return null;
    }
}
