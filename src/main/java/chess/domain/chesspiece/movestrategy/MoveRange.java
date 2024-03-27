package chess.domain.chesspiece.movestrategy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.ChessPieceType;
import java.util.ArrayList;
import java.util.List;

public class MoveRange {

    private final List<Square> moveRange;

    public MoveRange() {
        this.moveRange = new ArrayList<>();
    }

    void addForward(ChessBoard chessBoard, Square startSquare) {
        if (startSquare.isForwardMost()) {
            return;
        }
        Square forwardSquare = chessBoard.findForwardSquare(startSquare);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(forwardSquare);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(forwardSquare);
        }
    }

    void addContinuousForward(ChessBoard chessBoard, Square startSquare) {
        while (!startSquare.isForwardMost()) {
            Square forwardSquare = chessBoard.findForwardSquare(startSquare);
            ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(forwardSquare);
            if (!chessPiece.isEmptyChessPiece()) {
                break;
            }
            moveRange.add(forwardSquare);
            startSquare = forwardSquare;
        }
    }

    void addBackward(ChessBoard chessBoard, Square startSquare) {
        if (startSquare.isBackwardMost()) {
            return;
        }
        Square backwardSquare = chessBoard.findBackwardSquare(startSquare);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(backwardSquare);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(backwardSquare);
        }
    }

    void addContinuousBackward(ChessBoard chessBoard, Square startSquare) {
        while (!startSquare.isBackwardMost()) {
            Square backwardSquare = chessBoard.findBackwardSquare(startSquare);
            ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(backwardSquare);
            if (!chessPiece.isEmptyChessPiece()) {
                break;
            }
            moveRange.add(backwardSquare);
            startSquare = backwardSquare;
        }
    }

    void addLeft(ChessBoard chessBoard, Square startSquare) {
        if (startSquare.isLeftMost()) {
            return;
        }
        Square leftSquare = chessBoard.findLeftSquare(startSquare);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(leftSquare);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(leftSquare);
        }
    }

    void addContinuousLeft(ChessBoard chessBoard, Square startSquare) {
        while (!startSquare.isLeftMost()) {
            Square leftSquare = chessBoard.findLeftSquare(startSquare);
            ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(leftSquare);
            if (!chessPiece.isEmptyChessPiece()) {
                break;
            }
            moveRange.add(leftSquare);
            startSquare = leftSquare;
        }
    }

    void addRight(ChessBoard chessBoard, Square startSquare) {
        if (startSquare.isRightMost()) {
            return;
        }
        Square rightSquare = chessBoard.findRightSquare(startSquare);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(rightSquare);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(rightSquare);
        }
    }

    void addContinuousRight(ChessBoard chessBoard, Square startSquare) {
        while (!startSquare.isRightMost()) {
            Square rightSquare = chessBoard.findRightSquare(startSquare);
            ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(rightSquare);
            if (!chessPiece.isEmptyChessPiece()) {
                break;
            }
            moveRange.add(rightSquare);
            startSquare = rightSquare;
        }
    }

    void addLeftForwardDiagonal(ChessBoard chessBoard, Square startSquare) {
        if (startSquare.isLeftMost() || startSquare.isForwardMost()) {
            return;
        }
        Square leftForwardDiagonalSquare = chessBoard.findLeftForwardDiagonalSquare(startSquare);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(leftForwardDiagonalSquare);
        if (isChessPiecePawn(chessBoard, startSquare) && !chessPiece.isEmptyChessPiece()) {
            moveRange.add(leftForwardDiagonalSquare);
        }
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(leftForwardDiagonalSquare);
        }
    }

    void addContinuousLeftForwardDiagonal(ChessBoard chessBoard, Square startSquare) {
        while (!startSquare.isLeftMost() || !startSquare.isForwardMost()) {
            Square leftForwadDiagonalSquare = chessBoard.findLeftForwardDiagonalSquare(startSquare);
            ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(leftForwadDiagonalSquare);
            if (!chessPiece.isEmptyChessPiece()) {
                break;
            }
            moveRange.add(leftForwadDiagonalSquare);
            startSquare = leftForwadDiagonalSquare;
        }
    }

    void addRightForwardDiagonal(ChessBoard chessBoard, Square startSquare) {
        if (startSquare.isRightMost() || startSquare.isForwardMost()) {
            return;
        }
        Square rightForwardDiagonalSquare = chessBoard.findRightForwardDiagonalSquare(startSquare);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(rightForwardDiagonalSquare);
        if (isChessPiecePawn(chessBoard, startSquare) && !chessPiece.isEmptyChessPiece()) {
            moveRange.add(rightForwardDiagonalSquare);
        }
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(rightForwardDiagonalSquare);
        }
    }

    private boolean isChessPiecePawn(ChessBoard chessBoard, Square startSquare) {
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(startSquare);
        return chessPiece.getChessPieceType() == ChessPieceType.PAWN;
    }

    void addContinuousRightForwardDiagonal(ChessBoard chessBoard, Square startSquare) {
        while (!startSquare.isRightMost() || !startSquare.isForwardMost()) {
            Square rightforwadrdDiagonalSquare = chessBoard.findRightForwardDiagonalSquare(startSquare);
            ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(rightforwadrdDiagonalSquare);
            if (!chessPiece.isEmptyChessPiece()) {
                break;
            }
            moveRange.add(rightforwadrdDiagonalSquare);
            startSquare = rightforwadrdDiagonalSquare;
        }
    }

    void addLeftBackwardDiagonal(ChessBoard chessBoard, Square startSquare) {
        if (startSquare.isLeftMost() || startSquare.isBackwardMost()) {
            return;
        }
        Square leftBackwardDiagonalSquare = chessBoard.findLeftBackwardDiagonalSquare(startSquare);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(leftBackwardDiagonalSquare);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(leftBackwardDiagonalSquare);
        }
    }

    void addContinuousLeftBackwardDiagonal(ChessBoard chessBoard, Square startSquare) {
        while (!startSquare.isLeftMost() || !startSquare.isBackwardMost()) {
            Square leftBackwardDiagonalSquare = chessBoard.findLeftBackwardDiagonalSquare(startSquare);
            ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(leftBackwardDiagonalSquare);
            if (!chessPiece.isEmptyChessPiece()) {
                break;
            }
            moveRange.add(leftBackwardDiagonalSquare);
            startSquare = leftBackwardDiagonalSquare;
        }
    }

    void addRightBackwardDiagonal(ChessBoard chessBoard, Square startSquare) {
        if (startSquare.isRightMost() || startSquare.isBackwardMost()) {
            return;
        }
        Square rightBackwardDiagonalSquare = chessBoard.findRightBackwardDiagonalSquare(startSquare);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(rightBackwardDiagonalSquare);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(rightBackwardDiagonalSquare);
        }
    }

    void addContinuousRightBackwardDiagonal(ChessBoard chessBoard, Square startSquare) {
        while (!startSquare.isRightMost() || !startSquare.isBackwardMost()) {
            Square rightBackwardDiagonalSquare = chessBoard.findRightBackwardDiagonalSquare(startSquare);
            ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(rightBackwardDiagonalSquare);
            if (!chessPiece.isEmptyChessPiece()) {
                break;
            }
            moveRange.add(rightBackwardDiagonalSquare);
            startSquare = rightBackwardDiagonalSquare;
        }
    }

    void addBackwardRightLShape(ChessBoard chessBoard, Square startSquare) {
        addBackward(chessBoard, startSquare);
        addBackward(chessBoard, startSquare);
        addRight(chessBoard, startSquare);
    }

    void addBackwardLeftLShape(ChessBoard chessBoard, Square startSquare) {
        addBackward(chessBoard, startSquare);
        addBackward(chessBoard, startSquare);
        addLeft(chessBoard, startSquare);
    }

    void addForwardRightLShape(ChessBoard chessBoard, Square startSquare) {
        addForward(chessBoard, startSquare);
        addForward(chessBoard, startSquare);
        addRight(chessBoard, startSquare);
    }

    void addForwardLeftLShape(ChessBoard chessBoard, Square startSquare) {
        addForward(chessBoard, startSquare);
        addForward(chessBoard, startSquare);
        addLeft(chessBoard, startSquare);
    }

    void addLeftForwardLShape(ChessBoard chessBoard, Square startSquare) {
        addLeft(chessBoard, startSquare);
        addLeft(chessBoard, startSquare);
        addForward(chessBoard, startSquare);
    }

    void addLeftBackwardLShape(ChessBoard chessBoard, Square startSquare) {
        addLeft(chessBoard, startSquare);
        addLeft(chessBoard, startSquare);
        addBackward(chessBoard, startSquare);
    }

    void addRightForwardLShape(ChessBoard chessBoard, Square startSquare) {
        addRight(chessBoard, startSquare);
        addRight(chessBoard, startSquare);
        addForward(chessBoard, startSquare);
    }

    void addRightBackwardLShape(ChessBoard chessBoard, Square startSquare) {
        addRight(chessBoard, startSquare);
        addRight(chessBoard, startSquare);
        addBackward(chessBoard, startSquare);
    }

    public List<Square> getMoveRange() {
        return moveRange;
    }
}
