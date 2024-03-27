package chess.domain.chesspiece.movestrategy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import java.util.HashSet;
import java.util.Set;

public class KnightMoveStrategy implements MoveStrategy {

    @Override
    public void move(ChessBoard chessBoard, Square startSquare, Square targetSquare) {
        Set<Square> moveRange = new HashSet<>(createMoveRange(chessBoard, startSquare).getMoveRange());

        if (moveRange.contains(targetSquare)) {
            chessBoard.movePiece(startSquare, targetSquare);
        }
    }

    private MoveRange createMoveRange(ChessBoard chessBoard, Square startSquare) {
        MoveRange moveRange = new MoveRange();
        moveRange.addForwardLeftLShape(chessBoard, startSquare);
        moveRange.addForwardRightLShape(chessBoard, startSquare);
        moveRange.addBackwardLeftLShape(chessBoard, startSquare);
        moveRange.addBackwardRightLShape(chessBoard, startSquare);
        moveRange.addBackwardRightLShape(chessBoard, startSquare);
        moveRange.addLeftForwardLShape(chessBoard, startSquare);
        moveRange.addLeftBackwardLShape(chessBoard, startSquare);
        moveRange.addRightForwardLShape(chessBoard, startSquare);
        moveRange.addRightBackwardLShape(chessBoard, startSquare);
        return moveRange;
    }
}
