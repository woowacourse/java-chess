package domain;

import java.util.HashMap;
import java.util.Map;

public class Piece {

    private static final Map<String, Piece> CACHE = new HashMap<>();
    private final Type type;
    private final Color color;

    static {
        for (Type type : Type.values()) {
            putColorToPiece(type);
        }
    }

    private static void putColorToPiece(final Type type) {
        for (Color color : Color.values()) {
            CACHE.put(createKey(type, color), new Piece(type, color));
        }
    }

    private static String createKey(final Type type, final Color color) {
        return type.name() + color.name();
    }

    private Piece(final Type type, final Color color) {
        this.type = type;
        this.color = color;
    }

    public static Piece of(final Type type, final Color color) {
        return CACHE.get(createKey(type, color));
    }

    public Type getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

}
