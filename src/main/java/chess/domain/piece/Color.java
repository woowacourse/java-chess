package chess.domain.piece;

import java.util.Arrays;

public enum Color {

    BLACK {
        @Override
        public Color getReverseColor() {
            return WHITE;
        }
    },
    WHITE {
        @Override
        public Color getReverseColor() {
            return BLACK;
        }
    },
    EMPTY {
        @Override
        public Color getReverseColor() {
            return EMPTY;
        }
    };

    public static Color of(String value) {
        return Arrays.stream(values())
                .filter(color -> color.name()
                        .equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 Color 이름이 들어왔습니다."));
    }

    public abstract Color getReverseColor();

    public boolean isBlack() {
        return this == BLACK;
    }

    public boolean isEmpty() {
        return this == EMPTY;
    }

    public boolean isWhite() {
        return this == WHITE;
    }

    public String getName() {
        return name().toLowerCase();
    }
}
