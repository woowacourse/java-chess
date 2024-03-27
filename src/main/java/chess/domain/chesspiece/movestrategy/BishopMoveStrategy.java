package chess.domain.chesspiece.movestrategy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import java.util.List;

public class BishopMoveStrategy implements MoveStrategy {

    @Override
    public void move(ChessBoard chessBoard, Square startSquare, Square targetSquare) {
        List<Square> moveRange = createMoveRange(chessBoard, startSquare).getMoveRange();

        if (moveRange.contains(targetSquare)) {
            chessBoard.movePiece(startSquare, targetSquare);
        }
    }

    private MoveRange createMoveRange(ChessBoard chessBoard, Square startSquare) {
        MoveRange moveRange = new MoveRange();
        moveRange.addContinuousLeftForwardDiagonal(chessBoard, startSquare);
        moveRange.addContinuousRightForwardDiagonal(chessBoard, startSquare);
        moveRange.addContinuousLeftBackwardDiagonal(chessBoard, startSquare);
        moveRange.addContinuousRightBackwardDiagonal(chessBoard, startSquare);
        return moveRange;
    }
}
