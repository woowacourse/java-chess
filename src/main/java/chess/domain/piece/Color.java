package chess.domain.piece;

public enum Color {
    WHITE {
        @Override
        public Color nextTurn() {
            return BLACK;
        }

        @Override
        public boolean isEnemyColor(final Color color) {
            return color == BLACK;
        }
    },
    BLACK {
        @Override
        public Color nextTurn() {
            return WHITE;
        }

        @Override
        public boolean isEnemyColor(final Color color) {
            return color == WHITE;
        }
    },
    EMPTY {
        @Override
        public Color nextTurn() {
            throw new UnsupportedOperationException("EMPTY는 nextTurn을 지원하지 않습니다");
        }

        @Override
        public boolean isEnemyColor(final Color color) {
            throw new UnsupportedOperationException("Empty는 적이 존재하지 않습니다");
        }
    };

    public static Color initialTurn() {
        return WHITE;
    }

    public abstract Color nextTurn();

    public abstract boolean isEnemyColor(final Color color);
}
