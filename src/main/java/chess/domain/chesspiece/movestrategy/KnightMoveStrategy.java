package chess.domain.chesspiece.movestrategy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import java.util.HashSet;
import java.util.Set;

public class KnightMoveStrategy extends MoveDirection implements MoveStrategy {

    @Override
    public void move(ChessBoard chessBoard, Square startSquare, Square targetSquare) {
        Set<Square> moveRange = createMoveRange(chessBoard, startSquare);

        if (moveRange.contains(targetSquare)) {
            chessBoard.movePiece(startSquare, targetSquare);
        }
    }

    private Set<Square> createMoveRange(ChessBoard chessBoard, Square startSquare) {
        Set<Square> moveRange = new HashSet<>();
        addForwardLeftLShape(moveRange, startSquare, chessBoard);
        addForwardRightLShape(moveRange, startSquare, chessBoard);
        addBackwardLeftLShape(moveRange, startSquare, chessBoard);
        addBackwardRightLShape(moveRange, startSquare, chessBoard);
        addBackwardRightLShape(moveRange, startSquare, chessBoard);
        addLeftForwardLShape(moveRange, startSquare, chessBoard);
        addLeftBackwardLShape(moveRange, startSquare, chessBoard);
        addRightForwardLShape(moveRange, startSquare, chessBoard);
        addRightBackwardLShape(moveRange, startSquare, chessBoard);
        return moveRange;
    }
}
