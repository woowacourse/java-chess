package chess.chessview;

import java.util.HashMap;
import java.util.Map;

class ChessSymbolGenerator {
    private static ChessSymbolGenerator generator = null;
    private Map<String, String> whiteSymbols = new HashMap<>();
    private Map<String, String> blackSymbols = new HashMap<>();

    private ChessSymbolGenerator() {
        whiteSymbols.put("K", "\u2654");
        whiteSymbols.put("Q", "\u2655");
        whiteSymbols.put("R", "\u2656");
        whiteSymbols.put("B", "\u2657");
        whiteSymbols.put("N", "\u2658");
        whiteSymbols.put("P", "\u2659");

        blackSymbols.put("K", "\u265A");
        blackSymbols.put("Q", "\u265B");
        blackSymbols.put("R", "\u265C");
        blackSymbols.put("B", "\u265D");
        blackSymbols.put("N", "\u265E");
        blackSymbols.put("P", "\u265F");
    }

    static ChessSymbolGenerator getInstance() {
        if (generator == null) {
            generator = new ChessSymbolGenerator();
        }
        return generator;
    }

    String generateSymbol(String name, boolean isWhitePlayer) {
        return isWhitePlayer ? whiteSymbols.get(name) : blackSymbols.get(name);
    }
}
