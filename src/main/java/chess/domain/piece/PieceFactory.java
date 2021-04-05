package chess.domain.piece;

import java.util.HashMap;
import java.util.Map;

public class PieceFactory {
    private static final Map<Condition, Constructor> constructors;

    static {
        constructors = new HashMap<>();
//        constructors.put(x -> true, (color, x, y) -> new Bishop(color, x, y));
    }

    private PieceFactory() {
    }

    public static Piece from(char pieceName, Color color, char x, char y) {
        return constructors.entrySet()
                .stream()
                .filter(entry -> entry.getKey().match(pieceName))
                .map(Map.Entry::getValue)
                .map(constructor -> constructor.create(color, x, y))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("입력 값에 해당하는 말이 없습니다."));
    }
}
