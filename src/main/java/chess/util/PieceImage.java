package chess.util;

import chess.util.path.Resource;
import java.util.HashMap;
import java.util.Map;

public class PieceImage {

    private static final Map<String, String> cachedUrl = new HashMap<>();

    static {
        cachedUrl.put("BLACKq", Resource.BLACK_QUEEN);
        cachedUrl.put("BLACKr", Resource.BLACK_ROOK);
        cachedUrl.put("BLACKb", Resource.BLACK_BISHOP);
        cachedUrl.put("BLACKn", Resource.BLACK_KNIGHT);
        cachedUrl.put("BLACKp", Resource.BLACK_PAWN);
        cachedUrl.put("BLACKk", Resource.BLACK_KING);
        cachedUrl.put("WHITEq", Resource.WHITE_QUEEN);
        cachedUrl.put("WHITEr", Resource.WHITE_ROOK);
        cachedUrl.put("WHITEb", Resource.WHITE_BISHOP);
        cachedUrl.put("WHITEn", Resource.WHITE_KNIGHT);
        cachedUrl.put("WHITEp", Resource.WHITE_PAWN);
        cachedUrl.put("WHITEk", Resource.WHITE_KING);
    }

    public static String of(final String key) {
        return cachedUrl.getOrDefault(key, "");
    }
}
