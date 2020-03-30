package chess.domain2;

import chess.domain2.piece.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private Map<Square, Piece> chessBoard = new HashMap<>();
    private Color turn;

    public ChessBoard() {
        for (File file : File.values()) {
            InitializingChessBoard.initPiecesLocation(file, chessBoard);
        }
        this.turn = Color.WHITE;
    }

    public Map<Square, Piece> getChessBoard() {
        return chessBoard;
    }

    boolean canMove(Square beforeSquare, Square afterSquare) {
        Piece beforePiece = chessBoard.get(beforeSquare);
        if (isCanNotMove(beforeSquare, afterSquare, beforePiece)) {
            return false;
        }
        changeTurn();
        return true;
    }

    private boolean isCanNotMove(Square beforeSquare, Square afterSquare, Piece beforePiece) {
        if (!chessBoard.containsKey(beforeSquare)) {
            return true;
        }
        if (!beforePiece.getColor().equals(turn)) {
            return true;
        }
        if (!beforePiece.getMovableSquares(beforeSquare, chessBoard).contains(afterSquare)) {
            return true;
        }
        return false;
    }

    void changeTurn() {
        this.turn = this.turn.changeColor(turn);
    }

    public boolean movePiece(List<Square> squares) {
        Square beforeSquare = squares.get(0);
        Square afterSquare = squares.get(1);
        if (!canMove(beforeSquare, afterSquare)) {
            return false;
        }
        Piece currentPiece = chessBoard.remove(beforeSquare);
        chessBoard.put(afterSquare, currentPiece);
        return true;
    }
}
