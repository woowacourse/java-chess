package domain.board;

import java.util.HashMap;
import java.util.Map;

public final class SquareCache {
    private static final Map<SquareName, Square> SQUARE_CACHE = new HashMap<>();

    public static boolean contains(SquareName squareName) {
        return SQUARE_CACHE.containsKey(squareName);
    }

    public static Square getSquare(SquareName squareName) {
        return SQUARE_CACHE.get(squareName);
    }

    public static void putSquare(SquareName squareName, Square square) {
        SQUARE_CACHE.put(squareName, square);
    }
}
