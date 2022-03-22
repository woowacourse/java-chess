package chess.domain;

import chess.domain.chessPiece.*;

import java.util.HashMap;
import java.util.Map;

public class ChessBoard {

    private Map<String, ChessPiece> chessBoard;

    public ChessBoard() {
        chessBoard = new HashMap<>();
        init();
    }

    /*
Piece
row = a
column = 1
value = r
    */

    private void init() {
        chessBoard.put("a1", Rook.getInstance());
        chessBoard.put("b1", Rook.getInstance());
        chessBoard.put("c1", Knight.getInstance());
        chessBoard.put("d1", Knight.getInstance());
        chessBoard.put("e1", Bishop.getInstance());
        chessBoard.put("f1", Bishop.getInstance());
        chessBoard.put("g1", Queen.getInstance());
        chessBoard.put("h1", King.getInstance());

        chessBoard.put("a2", Pawn.getInstance());
        chessBoard.put("b2", Pawn.getInstance());
        chessBoard.put("c2", Pawn.getInstance());
        chessBoard.put("d2", Pawn.getInstance());
        chessBoard.put("e2", Pawn.getInstance());
        chessBoard.put("f2", Pawn.getInstance());
        chessBoard.put("g2", Pawn.getInstance());
        chessBoard.put("h2", Pawn.getInstance());

        chessBoard.put("a8", Rook.getInstance());
        chessBoard.put("b8", Rook.getInstance());
        chessBoard.put("c8", Knight.getInstance());
        chessBoard.put("d8", Knight.getInstance());
        chessBoard.put("e8", Bishop.getInstance());
        chessBoard.put("f8", Bishop.getInstance());
        chessBoard.put("g8", Queen.getInstance());
        chessBoard.put("h8", King.getInstance());

        chessBoard.put("a7", Pawn.getInstance());
        chessBoard.put("b7", Pawn.getInstance());
        chessBoard.put("c7", Pawn.getInstance());
        chessBoard.put("d7", Pawn.getInstance());
        chessBoard.put("e7", Pawn.getInstance());
        chessBoard.put("f7", Pawn.getInstance());
        chessBoard.put("g7", Pawn.getInstance());
        chessBoard.put("h7", Pawn.getInstance());
    }

    public int countPiece() {
        return chessBoard.size();
    }
}
