package chess.webutil;

import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.piece.Piece;

import java.util.HashMap;
import java.util.Map;

public class ModelParser {
    public static Map<String, Object> parseBlankBoard() {
        Map<String, Object> output = new HashMap<>();
        Board board = BoardFactory.createEmptyBoard();
        for (Piece piece : board.getBoard()) {
            output.put(piece.toString(), "blank");
        }
        return output;
    }

    public static Map<String, Object> parseBoard(final Board board) {
        Map<String, Object> output = new HashMap<>();
        for (Piece piece : board.getBoard()) {
            if (piece.isBlank()) {
                output.put(piece.toString(), "blank");
            } else {
                output.put(piece.toString(), piece.getName());
            }
        }
        return output;
    }
}
