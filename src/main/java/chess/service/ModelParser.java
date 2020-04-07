package chess.service;

import chess.domain.board.Board;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class ModelParser {
    public static Map<String, Object> parseBoard(Board board) {
        Map<String, Object> model = new HashMap<>();
        for (Piece piece : board.getBoard()) {
            model.put(piece.toString(), piece.getName());
        }
        return model;
    }
}
