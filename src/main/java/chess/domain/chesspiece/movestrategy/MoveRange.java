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

    public void addForward(ChessBoard chessBoard, Square startSquare) {
        if (startSquare.isForwardMost()) {
            return;
        }
        Square forwardSquare = chessBoard.findForwardSquare(startSquare);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(forwardSquare);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(forwardSquare);
        }
    }

    public void addContinuousForward(ChessBoard chessBoard, Square startSquare) {
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

    public void addBackward(ChessBoard chessBoard, Square startSquare) {
        if (startSquare.isBackwardMost()) {
            return;
        }
        Square backwardSquare = chessBoard.findBackwardSquare(startSquare);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(backwardSquare);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(backwardSquare);
        }
    }

    public void addContinuousBackward(ChessBoard chessBoard, Square startSquare) {
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

    public void addLeft(ChessBoard chessBoard, Square startSquare) {
        if (startSquare.isLeftMost()) {
            return;
        }
        Square leftSquare = chessBoard.findLeftSquare(startSquare);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(leftSquare);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(leftSquare);
        }
    }

    public void addContinuousLeft(ChessBoard chessBoard, Square startSquare) {
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

    public void addRight(ChessBoard chessBoard, Square startSquare) {
        if (startSquare.isRightMost()) {
            return;
        }
        Square rightSquare = chessBoard.findRightSquare(startSquare);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(rightSquare);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(rightSquare);
        }
    }

    public void addContinuousRight(ChessBoard chessBoard, Square startSquare) {
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

    public void addLeftForwardDiagonal(ChessBoard chessBoard, Square startSquare) {
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

    public void addContinuousLeftForwardDiagonal(ChessBoard chessBoard, Square startSquare) {
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

    public void addRightForwardDiagonal(ChessBoard chessBoard, Square startSquare) {
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

    public void addContinuousRightForwardDiagonal(ChessBoard chessBoard, Square startSquare) {
        Square square = startSquare;
        while (!square.isRightMost() && !square.isForwardMost()) {
            square = chessBoard.findRightForwardDiagonalSquare(square);
            ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(square);
            if (!chessPiece.isEmptyChessPiece()) {
                break;
            }
            moveRange.add(square);
        }
    }

    public void addLeftBackwardDiagonal(ChessBoard chessBoard, Square startSquare) {
        if (startSquare.isLeftMost() || startSquare.isBackwardMost()) {
            return;
        }
        Square leftBackwardDiagonalSquare = chessBoard.findLeftBackwardDiagonalSquare(startSquare);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(leftBackwardDiagonalSquare);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(leftBackwardDiagonalSquare);
        }
    }

    public void addContinuousLeftBackwardDiagonal(ChessBoard chessBoard, Square startSquare) {
        Square square = startSquare;
        while (!square.isLeftMost() && !square.isBackwardMost()) {
            square = chessBoard.findLeftBackwardDiagonalSquare(square);
            ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(square);
            if (!chessPiece.isEmptyChessPiece()) {
                break;
            }
            moveRange.add(square);
        }
    }

    public void addRightBackwardDiagonal(ChessBoard chessBoard, Square startSquare) {
        if (startSquare.isRightMost() || startSquare.isBackwardMost()) {
            return;
        }
        Square rightBackwardDiagonalSquare = chessBoard.findRightBackwardDiagonalSquare(startSquare);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(rightBackwardDiagonalSquare);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(rightBackwardDiagonalSquare);
        }
    }

    public void addContinuousRightBackwardDiagonal(ChessBoard chessBoard, Square startSquare) {
        Square square = startSquare;
        while (!square.isRightMost() && !square.isBackwardMost()) {
            square = chessBoard.findRightBackwardDiagonalSquare(square);
            ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(square);
            if (!chessPiece.isEmptyChessPiece()) {
                break;
            }
            moveRange.add(square);
        }
    }

    public void addBackwardRightLShape(ChessBoard chessBoard, Square startSquare) {
        Square square = startSquare;
        if (square.isBackwardMost()) {
            return;
        }
        square = chessBoard.findBackwardSquare(square);
        if (square.isBackwardMost()) {
            return;
        }
        square = chessBoard.findBackwardSquare(square);
        if (startSquare.isRightMost()) {
            return;
        }
        square = chessBoard.findRightSquare(square);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(square);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(square);
        }
    }

    public void addBackwardLeftLShape(ChessBoard chessBoard, Square startSquare) {
        Square square = startSquare;
        if (square.isBackwardMost()) {
            return;
        }
        square = chessBoard.findBackwardSquare(square);
        if (square.isBackwardMost()) {
            return;
        }
        square = chessBoard.findBackwardSquare(square);
        if (startSquare.isLeftMost()) {
            return;
        }
        square = chessBoard.findLeftSquare(square);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(square);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(square);
        }
    }

    public void addForwardRightLShape(ChessBoard chessBoard, Square startSquare) {
        Square square = startSquare;
        if (square.isForwardMost()) {
            return;
        }
        square = chessBoard.findForwardSquare(square);
        if (square.isForwardMost()) {
            return;
        }
        square = chessBoard.findForwardSquare(square);
        if (startSquare.isRightMost()) {
            return;
        }
        square = chessBoard.findRightSquare(square);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(square);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(square);
        }
    }

    public void addForwardLeftLShape(ChessBoard chessBoard, Square startSquare) {
        Square square = startSquare;
        if (square.isForwardMost()) {
            return;
        }
        square = chessBoard.findForwardSquare(square);
        if (square.isForwardMost()) {
            return;
        }
        square = chessBoard.findForwardSquare(square);
        if (startSquare.isLeftMost()) {
            return;
        }
        square = chessBoard.findLeftSquare(square);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(square);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(square);
        }
    }

    public void addLeftForwardLShape(ChessBoard chessBoard, Square startSquare) {
        Square square = startSquare;
        if (startSquare.isLeftMost()) {
            return;
        }
        square = chessBoard.findLeftSquare(square);
        if (startSquare.isLeftMost()) {
            return;
        }
        square = chessBoard.findLeftSquare(square);
        if (square.isForwardMost()) {
            return;
        }
        square = chessBoard.findForwardSquare(square);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(square);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(square);
        }
    }

    public void addLeftBackwardLShape(ChessBoard chessBoard, Square startSquare) {
        Square square = startSquare;
        if (startSquare.isLeftMost()) {
            return;
        }
        square = chessBoard.findLeftSquare(square);
        if (startSquare.isLeftMost()) {
            return;
        }
        square = chessBoard.findLeftSquare(square);
        if (square.isBackwardMost()) {
            return;
        }
        square = chessBoard.findBackwardSquare(square);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(square);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(square);
        }
    }

    public void addRightForwardLShape(ChessBoard chessBoard, Square startSquare) {
        Square square = startSquare;
        if (startSquare.isRightMost()) {
            return;
        }
        square = chessBoard.findRightSquare(square);
        if (startSquare.isRightMost()) {
            return;
        }
        square = chessBoard.findRightSquare(square);
        if (square.isForwardMost()) {
            return;
        }
        square = chessBoard.findForwardSquare(square);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(square);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(square);
        }
    }

    public void addRightBackwardLShape(ChessBoard chessBoard, Square startSquare) {
        Square square = startSquare;
        if (startSquare.isRightMost()) {
            return;
        }
        square = chessBoard.findRightSquare(square);
        if (startSquare.isRightMost()) {
            return;
        }
        square = chessBoard.findRightSquare(square);
        if (square.isBackwardMost()) {
            return;
        }
        square = chessBoard.findBackwardSquare(square);
        ChessPiece chessPiece = chessBoard.findChessPieceOnSquare(square);
        if (chessPiece.isEmptyChessPiece()) {
            moveRange.add(square);
        }
    }

    public List<Square> getMoveRange() {
        return moveRange;
    }
}
