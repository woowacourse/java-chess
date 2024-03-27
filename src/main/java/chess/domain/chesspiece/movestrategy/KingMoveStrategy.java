package chess.domain.chesspiece.movestrategy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import java.util.List;

public class KingMoveStrategy implements MoveStrategy {

    @Override
    public void move(ChessBoard chessBoard, Square startSquare, Square targetSquare) {
        List<Square> moveRange = createMoveRange(chessBoard, startSquare).getMoveRange();

        if (moveRange.contains(targetSquare)) {
            chessBoard.movePiece(startSquare, targetSquare);
        }
    }

    private MoveRange createMoveRange(ChessBoard chessBoard, Square startSquare) {
        MoveRange moveRange = new MoveRange();
        moveRange.addForward(chessBoard, startSquare);
        moveRange.addBackward(chessBoard, startSquare);
        moveRange.addLeft(chessBoard, startSquare);
        moveRange.addRight(chessBoard, startSquare);
        moveRange.addLeftForwardDiagonal(chessBoard, startSquare);
        moveRange.addRightForwardDiagonal(chessBoard, startSquare);
        moveRange.addLeftBackwardDiagonal(chessBoard, startSquare);
        moveRange.addRightBackwardDiagonal(chessBoard, startSquare);
        return moveRange;
    }
}
