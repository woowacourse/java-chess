package chess.domain.chesspiece.movestrategy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import java.util.List;

public class PawnMoveStrategy implements MoveStrategy {

    private boolean isStartingPosition = true;

    @Override
    public void move(ChessBoard chessBoard, Square startSquare, Square targetSquare) {
        List<Square> moveRange = createMoveRange(chessBoard, startSquare).getMoveRange();

        if (moveRange.contains(targetSquare)) {
            chessBoard.movePiece(startSquare, targetSquare);
            isStartingPosition = false;
        }
    }

    private MoveRange createMoveRange(ChessBoard chessBoard, Square startSquare) {
        MoveRange moveRange = new MoveRange();
        moveRange.addForward(chessBoard, startSquare);
        if (isStartingPosition) {
            moveRange.addForward(chessBoard, startSquare);
        }
        moveRange.addLeftForwardDiagonal(chessBoard, startSquare);
        moveRange.addRightForwardDiagonal(chessBoard, startSquare);

        return moveRange;
    }
}
