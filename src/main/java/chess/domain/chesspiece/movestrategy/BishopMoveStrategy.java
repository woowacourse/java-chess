package chess.domain.chesspiece.movestrategy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import java.util.ArrayList;
import java.util.List;

public class BishopMoveStrategy extends MoveDirection implements MoveStrategy {

    @Override
    public void move(ChessBoard chessBoard, Square startSquare, Square targetSquare) {
        List<Square> moveRange = createMoveRange(chessBoard, startSquare);

        if (moveRange.contains(targetSquare)) {
            chessBoard.movePiece(startSquare, targetSquare);
        }
    }

    private List<Square> createMoveRange(ChessBoard chessBoard, Square startSquare) {
        List<Square> moveRange = new ArrayList<>();
        addContinuousLeftForwardDiagonal(moveRange, startSquare, chessBoard);
        addContinuousRightForwardDiagonal(moveRange, startSquare, chessBoard);
        addContinuousLeftBackwardDiagonal(moveRange, startSquare, chessBoard);
        addContinuousRightBackwardDiagonal(moveRange, startSquare, chessBoard);

        return moveRange;
    }
}
