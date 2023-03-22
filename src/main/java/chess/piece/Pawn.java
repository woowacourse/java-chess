package chess.piece;

import chess.ChessBoard;
import chess.position.Direction;
import chess.position.MovablePosition;
import chess.position.Position;

public class Pawn extends ChessPiece {
    private static final int WHITE_PAWN_FIRST_Y = 2;
    private static final int BLACK_PAWN_FIRST_Y = 7;

    public Pawn(Shape shape, Side side) {
        super(shape, side);
    }

    @Override
    public MovablePosition findMovableRoute(ChessBoard chessBoard, Position sourcePosition) {
        MovablePosition movablePosition = new MovablePosition();
        if (getSide().equals(Side.WHITE)) {
            findWhitePawnRoute(chessBoard, sourcePosition, movablePosition);
        }
        if (getSide().equals(Side.BLACK)) {
            findBlackPawnRoute(chessBoard, sourcePosition, movablePosition);
        }
        return movablePosition;
    }

    private void findWhitePawnRoute(ChessBoard chessBoard, Position sourcePosition, MovablePosition movablePosition) {
        if (sourcePosition.getYPosition() == WHITE_PAWN_FIRST_Y) {
            movablePosition.findMovablePosition(chessBoard, sourcePosition, Direction.WHITE_PAWN_FIRST_MOVE_DIRECTION,
                    false);
        }
        movablePosition.findMovablePosition(chessBoard, sourcePosition, Direction.WHITE_PAWN_MOVE_DIRECTION, false);
        movablePosition.findMovablePosition(chessBoard, sourcePosition, Direction.WHITE_PAWN_CATCH_DIRECTION,
                false);
    }

    private void findBlackPawnRoute(ChessBoard chessBoard, Position sourcePosition, MovablePosition movablePosition) {
        if (sourcePosition.getYPosition() == BLACK_PAWN_FIRST_Y) {
            movablePosition.findMovablePosition(chessBoard, sourcePosition,
                    Direction.BLACK_PAWN_FIRST_MOVE_DIRECTION, false);
        }
        movablePosition.findMovablePosition(chessBoard, sourcePosition, Direction.BLACK_PAWN_MOVE_DIRECTION, false);
        movablePosition.findMovablePosition(chessBoard, sourcePosition, Direction.BLACK_PAWN_CATCH_DIRECTION,
                false);
    }
}
