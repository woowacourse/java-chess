package chess.domain.initialChessBoard;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InitialBlackChessBoardDTO {

    private static Map<String, String> initialBlackBoard = new HashMap<>();

    static {
        initialBlackBoard.put("a7", "p");
        initialBlackBoard.put("b7", "p");
        initialBlackBoard.put("c7", "p");
        initialBlackBoard.put("d7", "p");
        initialBlackBoard.put("e7", "p");
        initialBlackBoard.put("f7", "p");
        initialBlackBoard.put("g7", "p");
        initialBlackBoard.put("h7", "p");
        initialBlackBoard.put("a8", "r");
        initialBlackBoard.put("b8", "n");
        initialBlackBoard.put("c8", "b");
        initialBlackBoard.put("d8", "q");
        initialBlackBoard.put("e8", "k");
        initialBlackBoard.put("f8", "b");
        initialBlackBoard.put("g8", "n");
        initialBlackBoard.put("h8", "r");
    }

    public static Map<String, String> getInitialChessBoard() {
        return Collections.unmodifiableMap(initialBlackBoard);
    }
}
