package chess.domain.chesspiece.movestrategy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import java.util.ArrayList;
import java.util.List;

public class RookMoveStrategy implements MoveStrategy {

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

        return moveRange;
    }

    private void addForward(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        while (!startSquare.isForwardMost()) {
            Square forwardSquare = chessBoard.findForwardSquare(startSquare);
            if (chessBoard.findChessPieceOnSquare(forwardSquare).isPresent()) {
                break;
            }
            moveRange.add(forwardSquare);
            startSquare = forwardSquare;
        }
    }

    private void addBackward(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        while (!startSquare.isBackwardMost()) {
            Square backwardSquare = chessBoard.findBackwardSquare(startSquare);
            if (chessBoard.findChessPieceOnSquare(backwardSquare).isPresent()) {
                break;
            }
            moveRange.add(backwardSquare);
            startSquare = backwardSquare;
        }
    }

    private void addLeft(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        while (!startSquare.isLeftMost()) {
            Square leftSquare = chessBoard.findLeftSquare(startSquare);
            if (chessBoard.findChessPieceOnSquare(leftSquare).isPresent()) {
                break;
            }
            moveRange.add(leftSquare);
            startSquare = leftSquare;
        }
    }

    private void addRight(List<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        while (!startSquare.isRightMost()) {
            Square rightSquare = chessBoard.findRightSquare(startSquare);
            if (chessBoard.findChessPieceOnSquare(rightSquare).isPresent()) {
                break;
            }
            moveRange.add(rightSquare);
            startSquare = rightSquare;
        }
    }
}
