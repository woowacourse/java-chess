package chess.domain.initialChessBoard;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InitialWhiteChessBoardDTO {
    private static Map<String, String> initialBlackBoard = new HashMap<>();

    static {
        initialBlackBoard.put("a2", "P");
        initialBlackBoard.put("b2", "P");
        initialBlackBoard.put("c2", "P");
        initialBlackBoard.put("d2", "P");
        initialBlackBoard.put("e2", "P");
        initialBlackBoard.put("f2", "P");
        initialBlackBoard.put("g2", "P");
        initialBlackBoard.put("h2", "P");
        initialBlackBoard.put("a1", "R");
        initialBlackBoard.put("b1", "N");
        initialBlackBoard.put("c1", "B");
        initialBlackBoard.put("d1", "Q");
        initialBlackBoard.put("e1", "K");
        initialBlackBoard.put("f1", "B");
        initialBlackBoard.put("g1", "N");
        initialBlackBoard.put("h1", "R");
    }

    public static Map<String, String> getInitialChessBoard() {
        return Collections.unmodifiableMap(initialBlackBoard);
    }
}
