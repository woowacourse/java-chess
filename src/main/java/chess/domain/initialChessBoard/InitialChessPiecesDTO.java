package chess.domain.initialChessBoard;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InitialChessPiecesDTO {
    private static Map<String, String> initialChessPieces = new HashMap<>();

    static {
        initialChessPieces.put("b7", "p");
        initialChessPieces.put("c7", "p");
        initialChessPieces.put("a7", "p");
        initialChessPieces.put("d7", "p");
        initialChessPieces.put("e7", "p");
        initialChessPieces.put("f7", "p");
        initialChessPieces.put("g7", "p");
        initialChessPieces.put("h7", "p");
        initialChessPieces.put("a8", "r");
        initialChessPieces.put("b8", "n");
        initialChessPieces.put("c8", "b");
        initialChessPieces.put("d8", "q");
        initialChessPieces.put("e8", "k");
        initialChessPieces.put("f8", "b");
        initialChessPieces.put("g8", "n");
        initialChessPieces.put("h8", "r");
        initialChessPieces.put("a2", "P");
        initialChessPieces.put("b2", "P");
        initialChessPieces.put("c2", "P");
        initialChessPieces.put("d2", "P");
        initialChessPieces.put("e2", "P");
        initialChessPieces.put("f2", "P");
        initialChessPieces.put("g2", "P");
        initialChessPieces.put("h2", "P");
        initialChessPieces.put("a1", "R");
        initialChessPieces.put("b1", "N");
        initialChessPieces.put("c1", "B");
        initialChessPieces.put("d1", "Q");
        initialChessPieces.put("e1", "K");
        initialChessPieces.put("f1", "B");
        initialChessPieces.put("g1", "N");
        initialChessPieces.put("h1", "R");
    }

    public static Map<String, String> getInitialChessBoard() {
        return Collections.unmodifiableMap(initialChessPieces);
    }
}
