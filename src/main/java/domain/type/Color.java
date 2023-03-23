package domain.type;

import java.util.Arrays;

public enum Color {
    WHITE, BLACK, NONE;

    public static Color findByName(final String name) {
        return Arrays.stream(Color.values())
            .filter(color -> color.name().equalsIgnoreCase(name))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("적절하지 않은 색입니다."));
    }

    public Color reverse() {
        if (this.equals(WHITE)) {
            return BLACK;
        }
        if (this.equals(BLACK)) {
            return WHITE;
        }
        return NONE;
    }
}
