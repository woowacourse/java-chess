package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Type;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {

    private static final int FIRST_KINGS_NUMBER = 2;
    private Map<Square, Piece> chessBoard = new HashMap<>();
    private Turn turn;

    public ChessBoard() {
        for (File file : File.values()) {
            InitializingChessBoard.initPiecesLocation(file, chessBoard);
        }
        this.turn = new Turn();
    }

    public Map<Square, Piece> getChessBoard() {
        return chessBoard;
    }

    boolean canMove(Square beforeSquare, Square afterSquare) {
        Piece beforePiece = chessBoard.get(beforeSquare);
        if (!chessBoard.containsKey(beforeSquare)) {
            return false;
        }
        if (!beforePiece.getColor().equals(turn.getTurn())) {
            return false;
        }
        if (!beforePiece.getMovableSquares(beforeSquare, chessBoard).contains(afterSquare)) {
            return false;
        }
        turn.changeTurn();
        return true;
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

    public boolean isKingCaptured() {
        return chessBoard.values().stream()
                .filter(piece -> piece.getLetter().equals(Type.KING.getName())
                        || piece.getLetter().equals(Type.KING.getName().toLowerCase()))
                .toArray().length != FIRST_KINGS_NUMBER;
    }

    Turn getTurn() {
        return turn;
    }
}
