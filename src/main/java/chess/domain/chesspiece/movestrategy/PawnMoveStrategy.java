package chess.domain.chesspiece.movestrategy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import java.util.ArrayList;
import java.util.List;

public class PawnMoveStrategy extends MoveDirection implements MoveStrategy {

    private boolean isStartingPosition = true;

    @Override
    public void move(ChessBoard chessBoard, Square startSquare, Square targetSquare) {
        List<Square> moveRange = createMoveRange(chessBoard, startSquare);

        if (moveRange.contains(targetSquare)) {
            chessBoard.movePiece(startSquare, targetSquare);
            isStartingPosition = false;
        }
    }

    private List<Square> createMoveRange(ChessBoard chessBoard, Square startSquare) {
        List<Square> moveRange = new ArrayList<>();
        addForward(moveRange, startSquare, chessBoard);
        if (isStartingPosition) {
            addForward(moveRange, startSquare, chessBoard);
        }
        addLeftForwardDiagonal(moveRange, startSquare, chessBoard);
        addRightForwardDiagonal(moveRange, startSquare, chessBoard);

        return moveRange;
    }

    @Override
    void addLeftForwardDiagonal(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isLeftMost() || startSquare.isForwardMost()) {
            return;
        }
        Square leftDiagonalSquare = chessBoard.findLeftForwardDiagonalSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(leftDiagonalSquare).isPresent()) {
            moveRange.add(leftDiagonalSquare);
        }
    }

    @Override
    void addRightForwardDiagonal(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isRightMost() || startSquare.isForwardMost()) {
            return;
        }
        Square rightDiagonalSquare = chessBoard.findRightForwardDiagonalSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(rightDiagonalSquare).isPresent()) {
            moveRange.add(rightDiagonalSquare);
        }
    }
}
