package chess.board;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import chess.piece.Piece;

public class Board {
    private final Map<Square, Piece> board;

    public Board() {
        Map<Square, Piece> board = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            createRow(board, rank);
        }
        this.board = board;
    }

    private void createRow(Map<Square, Piece> board, Rank rank) {
        for (File file : File.values()) {
            board.put(new Square(file, rank), Piece.from(file, rank));
        }
    }

    public List<List<Piece>> splitByRank() {
        List<Piece> pieces = new ArrayList<>(board.values());
        List<List<Piece>> splitPieces = Lists.partition(pieces, Rank.values().length);
        return reverse(splitPieces);
    }

    private List<List<Piece>> reverse(List<List<Piece>> splitPieces) {
        List<List<Piece>> result = new ArrayList<>();
        for (List<Piece> splitPiece : splitPieces) {
            result.add(0, splitPiece);
        }
        return result;
    }
}
