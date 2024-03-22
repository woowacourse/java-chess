package chess.domain.chesspiece.movestrategy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import java.util.ArrayList;
import java.util.List;

public class BishopMoveStrategy implements MoveStrategy {

    @Override
    public void move(ChessBoard chessBoard, Square startSquare, Square targetSquare) {
        List<Square> moveRange = createMoveRange(chessBoard, startSquare);

        if (moveRange.contains(targetSquare)) {
            chessBoard.movePiece(startSquare, targetSquare);
        }
    }

    private List<Square> createMoveRange(ChessBoard chessBoard, Square startSquare) {
        List<Square> moveRange = new ArrayList<>();
        addLeftForwardDiagonal(moveRange, startSquare, chessBoard);
        addRightForwardDiagonal(moveRange, startSquare, chessBoard);
        addLeftBackwardDiagonal(moveRange, startSquare, chessBoard);
        addRightBackwardDiagonal(moveRange, startSquare, chessBoard);

        return moveRange;
    }

    private void addLeftForwardDiagonal(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        while (!startSquare.isLeftMost() || !startSquare.isForwardMost()) {
            Square leftForwadDiagonalSquare = chessBoard.findLeftForwardDiagonalSquare(startSquare);
            if (chessBoard.findChessPieceOnSquare(leftForwadDiagonalSquare).isPresent()) {
                break;
            }
            moveRange.add(leftForwadDiagonalSquare);
            startSquare = leftForwadDiagonalSquare;
        }
    }

    private void addRightForwardDiagonal(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        while (!startSquare.isRightMost() || !startSquare.isForwardMost()) {
            Square rightforwadrdDiagonalSquare = chessBoard.findRightForwardDiagonalSquare(startSquare);
            if (chessBoard.findChessPieceOnSquare(rightforwadrdDiagonalSquare).isPresent()) {
                break;
            }
            moveRange.add(rightforwadrdDiagonalSquare);
            startSquare = rightforwadrdDiagonalSquare;
        }
    }

    private void addLeftBackwardDiagonal(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        while (!startSquare.isLeftMost() || !startSquare.isBackwardMost()) {
            Square leftBackwardDiagonalSquare = chessBoard.findLeftBackwardDiagonalSquare(startSquare);
            if (chessBoard.findChessPieceOnSquare(leftBackwardDiagonalSquare).isPresent()) {
                break;
            }
            moveRange.add(leftBackwardDiagonalSquare);
            startSquare = leftBackwardDiagonalSquare;
        }
    }

    private void addRightBackwardDiagonal(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        while (!startSquare.isRightMost() || !startSquare.isBackwardMost()) {
            Square rightBackwardDiagonalSquare = chessBoard.findRightBackwardDiagonalSquare(startSquare);
            if (chessBoard.findChessPieceOnSquare(rightBackwardDiagonalSquare).isPresent()) {
                break;
            }
            moveRange.add(rightBackwardDiagonalSquare);
            startSquare = rightBackwardDiagonalSquare;
        }
    }
}
