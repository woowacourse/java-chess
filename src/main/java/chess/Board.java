package chess;

import java.util.HashMap;
import java.util.Map;

import chess.piece.Piece;

public class Board {
    private final Map<Square, Piece> board;

    public Board() {
        Map<Square, Piece> board = new HashMap<>();
        for (Rank rank : Rank.values()) {
            createRow(board, rank);
        }
        this.board = board;
    }

    private void createRow(Map<Square, Piece> board, Rank rank) {
        for (File file : File.values()) {
            board.put(new Square(file, rank), Piece.from(file,rank));
        }
    }
}
