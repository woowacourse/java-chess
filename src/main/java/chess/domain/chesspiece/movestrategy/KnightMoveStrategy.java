package chess.domain.chesspiece.movestrategy;

import chess.domain.chessboard.ChessBoard;
import chess.domain.chessboard.Square;
import java.util.HashSet;
import java.util.Set;

public class KnightMoveStrategy implements MoveStrategy {

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

    private void addBackwardRightLShape(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        addBackward(moveRange, startSquare, chessBoard);
        addBackward(moveRange, startSquare, chessBoard);
        addRight(moveRange, startSquare, chessBoard);
    }

    private void addBackwardLeftLShape(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        addBackward(moveRange, startSquare, chessBoard);
        addBackward(moveRange, startSquare, chessBoard);
        addLeft(moveRange, startSquare, chessBoard);
    }

    private void addForwardRightLShape(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        addForward(moveRange, startSquare, chessBoard);
        addForward(moveRange, startSquare, chessBoard);
        addRight(moveRange, startSquare, chessBoard);
    }

    private void addForwardLeftLShape(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        addForward(moveRange, startSquare, chessBoard);
        addForward(moveRange, startSquare, chessBoard);
        addLeft(moveRange, startSquare, chessBoard);
    }

    private void addLeftForwardLShape(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        addLeft(moveRange, startSquare, chessBoard);
        addLeft(moveRange, startSquare, chessBoard);
        addForward(moveRange, startSquare, chessBoard);
    }

    private void addLeftBackwardLShape(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        addLeft(moveRange, startSquare, chessBoard);
        addLeft(moveRange, startSquare, chessBoard);
        addBackward(moveRange, startSquare, chessBoard);
    }

    private void addRightForwardLShape(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
        addRight(moveRange, startSquare, chessBoard);
        addRight(moveRange, startSquare, chessBoard);
        addForward(moveRange, startSquare, chessBoard);
    }

    private void addRightBackwardLShape(Set<Square> moveRange, Square startSquare, ChessBoard chessBoard) {
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
