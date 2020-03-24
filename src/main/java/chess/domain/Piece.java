package chess.domain;

import java.util.HashMap;
import java.util.Map;

public class Piece {
    private final static Map<String, Piece> cache = new HashMap<>();

    static {
        for (Color color : Color.values()) {
            for (Type type : Type.values()) {
                cache.put(color.getName() + type.getName(), new Piece(color, type));
            }
        }
    }

    private final Color color;
    private final Type type;

    private Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public static Piece of(Color color, Type type) {
        return cache.get(color.getName() + type.getName());
    }
}
