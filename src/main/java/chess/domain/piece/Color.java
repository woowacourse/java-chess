package chess.domain.piece;

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
}
