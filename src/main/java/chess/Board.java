package chess;

import chess.piece.*;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<String, Piece> board = new HashMap<>();

    public Board() {
        board.put("a8", new Rook(Color.BLACK));
        board.put("b8", new Knight(Color.BLACK));
        board.put("c8", new Bishop(Color.BLACK));
        board.put("d8", new Queen(Color.BLACK));
        board.put("e8", new King(Color.BLACK));
        board.put("f8", new Bishop(Color.BLACK));
        board.put("g8", new Knight(Color.BLACK));
        board.put("h8", new Rook(Color.BLACK));

        board.put("a7", new Pawn(Color.BLACK));
        board.put("b7", new Pawn(Color.BLACK));
        board.put("c7", new Pawn(Color.BLACK));
        board.put("d7", new Pawn(Color.BLACK));
        board.put("e7", new Pawn(Color.BLACK));
        board.put("f7", new Pawn(Color.BLACK));
        board.put("g7", new Pawn(Color.BLACK));
        board.put("h7", new Pawn(Color.BLACK));

        board.put("a1", new Rook(Color.WHITE));
        board.put("b1", new Knight(Color.WHITE));
        board.put("c1", new Bishop(Color.WHITE));
        board.put("d1", new Queen(Color.WHITE));
        board.put("e1", new King(Color.WHITE));
        board.put("f1", new Bishop(Color.WHITE));
        board.put("g1", new Knight(Color.WHITE));
        board.put("h1", new Rook(Color.WHITE));

        board.put("a2", new Pawn(Color.WHITE));
        board.put("b2", new Pawn(Color.WHITE));
        board.put("c2", new Pawn(Color.WHITE));
        board.put("d2", new Pawn(Color.WHITE));
        board.put("e2", new Pawn(Color.WHITE));
        board.put("f2", new Pawn(Color.WHITE));
        board.put("g2", new Pawn(Color.WHITE));
        board.put("h2", new Pawn(Color.WHITE));
    }

    public boolean exist(String position, Class<? extends Piece> type, Color color) {
        Piece piece = board.get(position);
        return piece.isSameType(type) && piece.isSameColor(color);
    }
}
