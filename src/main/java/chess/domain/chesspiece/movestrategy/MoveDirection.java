package chess.domain.chesspiece.movestrategy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import java.util.List;
import java.util.Set;

public class MoveDirection {

    MoveDirection() {
    }

    void addForward(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isForwardMost()) {
            return;
        }
        Square forwardSquare = chessBoard.findForwardSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(forwardSquare).isEmpty()) {
            moveRange.add(forwardSquare);
        }
    }

    void addContinuousForward(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        while (!startSquare.isForwardMost()) {
            Square forwardSquare = chessBoard.findForwardSquare(startSquare);
            if (chessBoard.findChessPieceOnSquare(forwardSquare).isPresent()) {
                break;
            }
            moveRange.add(forwardSquare);
            startSquare = forwardSquare;
        }
    }

    void addBackward(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isBackwardMost()) {
            return;
        }
        Square backwardSquare = chessBoard.findBackwardSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(backwardSquare).isEmpty()) {
            moveRange.add(backwardSquare);
        }
    }

    void addContinuousBackward(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        while (!startSquare.isBackwardMost()) {
            Square backwardSquare = chessBoard.findBackwardSquare(startSquare);
            if (chessBoard.findChessPieceOnSquare(backwardSquare).isPresent()) {
                break;
            }
            moveRange.add(backwardSquare);
            startSquare = backwardSquare;
        }
    }

    void addLeft(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isLeftMost()) {
            return;
        }
        Square leftSquare = chessBoard.findLeftSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(leftSquare).isEmpty()) {
            moveRange.add(leftSquare);
        }
    }

    void addContinuousLeft(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        while (!startSquare.isLeftMost()) {
            Square leftSquare = chessBoard.findLeftSquare(startSquare);
            if (chessBoard.findChessPieceOnSquare(leftSquare).isPresent()) {
                break;
            }
            moveRange.add(leftSquare);
            startSquare = leftSquare;
        }
    }

    void addRight(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isRightMost()) {
            return;
        }
        Square rightSquare = chessBoard.findRightSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(rightSquare).isEmpty()) {
            moveRange.add(rightSquare);
        }
    }

    void addContinuousRight(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        while (!startSquare.isRightMost()) {
            Square rightSquare = chessBoard.findRightSquare(startSquare);
            if (chessBoard.findChessPieceOnSquare(rightSquare).isPresent()) {
                break;
            }
            moveRange.add(rightSquare);
            startSquare = rightSquare;
        }
    }

    void addLeftForwardDiagonal(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isLeftMost() || startSquare.isForwardMost()) {
            return;
        }
        Square leftForwardDiagonalSquare = chessBoard.findLeftForwardDiagonalSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(leftForwardDiagonalSquare).isEmpty()) {
            moveRange.add(leftForwardDiagonalSquare);
        }
    }

    void addContinuousLeftForwardDiagonal(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        while (!startSquare.isLeftMost() || !startSquare.isForwardMost()) {
            Square leftForwadDiagonalSquare = chessBoard.findLeftForwardDiagonalSquare(startSquare);
            if (chessBoard.findChessPieceOnSquare(leftForwadDiagonalSquare).isPresent()) {
                break;
            }
            moveRange.add(leftForwadDiagonalSquare);
            startSquare = leftForwadDiagonalSquare;
        }
    }

    void addRightForwardDiagonal(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isRightMost() || startSquare.isForwardMost()) {
            return;
        }
        Square rightForwardDiagonalSquare = chessBoard.findRightForwardDiagonalSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(rightForwardDiagonalSquare).isEmpty()) {
            moveRange.add(rightForwardDiagonalSquare);
        }
    }

    void addContinuousRightForwardDiagonal(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        while (!startSquare.isRightMost() || !startSquare.isForwardMost()) {
            Square rightforwadrdDiagonalSquare = chessBoard.findRightForwardDiagonalSquare(startSquare);
            if (chessBoard.findChessPieceOnSquare(rightforwadrdDiagonalSquare).isPresent()) {
                break;
            }
            moveRange.add(rightforwadrdDiagonalSquare);
            startSquare = rightforwadrdDiagonalSquare;
        }
    }

    void addLeftBackwardDiagonal(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isLeftMost() || startSquare.isBackwardMost()) {
            return;
        }
        Square leftBackwardDiagonalSquare = chessBoard.findLeftBackwardDiagonalSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(leftBackwardDiagonalSquare).isEmpty()) {
            moveRange.add(leftBackwardDiagonalSquare);
        }
    }

    void addContinuousLeftBackwardDiagonal(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        while (!startSquare.isLeftMost() || !startSquare.isBackwardMost()) {
            Square leftBackwardDiagonalSquare = chessBoard.findLeftBackwardDiagonalSquare(startSquare);
            if (chessBoard.findChessPieceOnSquare(leftBackwardDiagonalSquare).isPresent()) {
                break;
            }
            moveRange.add(leftBackwardDiagonalSquare);
            startSquare = leftBackwardDiagonalSquare;
        }
    }

    void addRightBackwardDiagonal(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isRightMost() || startSquare.isBackwardMost()) {
            return;
        }
        Square rightBackwardDiagonalSquare = chessBoard.findRightBackwardDiagonalSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(rightBackwardDiagonalSquare).isEmpty()) {
            moveRange.add(rightBackwardDiagonalSquare);
        }
    }

    void addContinuousRightBackwardDiagonal(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        while (!startSquare.isRightMost() || !startSquare.isBackwardMost()) {
            Square rightBackwardDiagonalSquare = chessBoard.findRightBackwardDiagonalSquare(startSquare);
            if (chessBoard.findChessPieceOnSquare(rightBackwardDiagonalSquare).isPresent()) {
                break;
            }
            moveRange.add(rightBackwardDiagonalSquare);
            startSquare = rightBackwardDiagonalSquare;
        }
    }

    void addBackwardRightLShape(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        addBackward(moveRange, startSquare, chessBoard);
        addBackward(moveRange, startSquare, chessBoard);
        addRight(moveRange, startSquare, chessBoard);
    }

    void addBackwardLeftLShape(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        addBackward(moveRange, startSquare, chessBoard);
        addBackward(moveRange, startSquare, chessBoard);
        addLeft(moveRange, startSquare, chessBoard);
    }

    void addForwardRightLShape(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        addForward(moveRange, startSquare, chessBoard);
        addForward(moveRange, startSquare, chessBoard);
        addRight(moveRange, startSquare, chessBoard);
    }

    void addForwardLeftLShape(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        addForward(moveRange, startSquare, chessBoard);
        addForward(moveRange, startSquare, chessBoard);
        addLeft(moveRange, startSquare, chessBoard);
    }

    void addLeftForwardLShape(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        addLeft(moveRange, startSquare, chessBoard);
        addLeft(moveRange, startSquare, chessBoard);
        addForward(moveRange, startSquare, chessBoard);
    }

    void addLeftBackwardLShape(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        addLeft(moveRange, startSquare, chessBoard);
        addLeft(moveRange, startSquare, chessBoard);
        addBackward(moveRange, startSquare, chessBoard);
    }

    void addRightForwardLShape(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        addRight(moveRange, startSquare, chessBoard);
        addRight(moveRange, startSquare, chessBoard);
        addForward(moveRange, startSquare, chessBoard);
    }

    void addRightBackwardLShape(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        addRight(moveRange, startSquare, chessBoard);
        addRight(moveRange, startSquare, chessBoard);
        addBackward(moveRange, startSquare, chessBoard);
    }

    private void addForward(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isForwardMost()) {
            return;
        }
        Square forwardSquare = chessBoard.findForwardSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(forwardSquare).isEmpty()) {
            moveRange.add(forwardSquare);
        }
    }

    private void addBackward(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isBackwardMost()) {
            return;
        }
        Square backwardSquare = chessBoard.findBackwardSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(backwardSquare).isEmpty()) {
            moveRange.add(backwardSquare);
        }
    }

    private void addLeft(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isLeftMost()) {
            return;
        }
        Square leftSquare = chessBoard.findLeftSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(leftSquare).isEmpty()) {
            moveRange.add(leftSquare);
        }
    }

    private void addRight(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        if (startSquare.isRightMost()) {
            return;
        }
        Square rightSquare = chessBoard.findRightSquare(startSquare);
        if (chessBoard.findChessPieceOnSquare(rightSquare).isEmpty()) {
            moveRange.add(rightSquare);
        }
    }
}
