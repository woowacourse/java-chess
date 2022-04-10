package chess.converter;

import chess.domain.board.Board;
import chess.domain.game.StatusCalculator;
import java.util.HashMap;
import java.util.Map;

public class Converter {

    public static Map<String, Object> toMap(int gameId, Board board, StatusCalculator statusCalculator) {
        Map<String, Object> map = toMap(gameId, board);
        map.putAll(statusCalculator.createStatus());
        return map;
    }

    public static Map<String, Object> toMap(int gameId, Board board) {
        Map<String, Object> map = new HashMap<>();
        map.putAll(board.toMap());
        map.put("id", gameId);
        return map;
    }
}

