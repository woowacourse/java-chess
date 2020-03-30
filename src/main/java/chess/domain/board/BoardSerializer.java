package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

public class BoardSerializer {
    public static Map<String, String> serialize(Board board) {
        Map<String, String> serialized = new HashMap<>();
        board.getPieces()
                .forEach((key, value) -> serialized.put(key.toString(), value.toString()));
        return serialized;
    }
}
