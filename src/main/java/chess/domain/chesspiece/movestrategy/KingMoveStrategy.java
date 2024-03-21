package chess.domain.chesspiece.movestrategy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import java.util.ArrayList;
import java.util.List;

public class KingMoveStrategy implements MoveStrategy {

    @Override
    public void move(ChessBoard chessBoard, Square startSquare, Square targetSquare) {
        List<Square> moveRange = createMoveRange(chessBoard, startSquare);

        if (moveRange.contains(targetSquare)) {
            chessBoard.movePiece(startSquare, targetSquare);
        }
    }

    private List<Square> createMoveRange(ChessBoard chessBoard, Square startSquare) {
        List<Square> moveRange = new ArrayList<>();
        addForward(moveRange, startSquare, chessBoard);
        addBackward(moveRange, startSquare, chessBoard);
        addLeft(moveRange, startSquare, chessBoard);
        addRight(moveRange, startSquare, chessBoard);
        addLeftForwardDiagonal(moveRange, startSquare, chessBoard);
        addRightForwardDiagonal(moveRange, startSquare, chessBoard);
        addLeftBackwardDiagonal(moveRange, startSquare, chessBoard);
        addRightBackwardDiagonal(moveRange, startSquare, chessBoard);

        return moveRange;
    }

    private void addForward(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isForwardMost()) {
            return;
        }

        Square forwardSquare = chessBoard.findForwardSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(forwardSquare).isEmpty()) {
            moveRange.add(forwardSquare);
        }
    }

    private void addBackward(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isBackwardMost()) {
            return;
        }

        Square backwardSquare = chessBoard.findBackwardSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(backwardSquare).isEmpty()) {
            moveRange.add(backwardSquare);
        }
    }

    private void addLeft(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isLeftMost()) {
            return;
        }

        Square leftSquare = chessBoard.findLeftSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(leftSquare).isEmpty()) {
            moveRange.add(leftSquare);
        }
    }

    private void addRight(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isRightMost()) {
            return;
        }

        Square rightSquare = chessBoard.findRightSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(rightSquare).isEmpty()) {
            moveRange.add(rightSquare);
        }
    }

    private void addLeftForwardDiagonal(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isLeftMost() || startSquare.isForwardMost()) {
            return;
        }

        Square leftForwardDiagonalSquare = chessBoard.findLeftForwardDiagonalSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(leftForwardDiagonalSquare).isEmpty()) {
            moveRange.add(leftForwardDiagonalSquare);
        }
    }

    private void addRightForwardDiagonal(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isRightMost() || startSquare.isForwardMost()) {
            return;
        }

        Square rightForwardDiagonalSquare = chessBoard.findRightForwardDiagonalSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(rightForwardDiagonalSquare).isEmpty()) {
            moveRange.add(rightForwardDiagonalSquare);
        }
    }

    private void addLeftBackwardDiagonal(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isLeftMost() || startSquare.isBackwardMost()) {
            return;
        }

        Square leftBackwardDiagonalSquare = chessBoard.findLeftBackwardDiagonalSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(leftBackwardDiagonalSquare).isEmpty()) {
            moveRange.add(leftBackwardDiagonalSquare);
        }
    }

    private void addRightBackwardDiagonal(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isRightMost() || startSquare.isBackwardMost()) {
            return;
        }

        Square rightBackwardDiagonalSquare = chessBoard.findRightBackwardDiagonalSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(rightBackwardDiagonalSquare).isEmpty()) {
            moveRange.add(rightBackwardDiagonalSquare);
        }
    }
}
