package chess.piece;

import chess.ChessBoard;
import chess.position.MovablePosition;
import chess.position.Position;

public class Empty extends ChessPiece {

    public Empty(Side side) {
        super(side);
    }

    @Override
    public MovablePosition findMovableRoute(ChessBoard chessBoard, Position sourcePosition) {
        return null;
    }
}
