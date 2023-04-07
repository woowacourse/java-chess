package chess.domain.piece;

public enum Color {
    WHITE {
        @Override
        public Color nextTurn() {
            return BLACK;
        }
    },
    BLACK {
        @Override
        public Color nextTurn() {
            return WHITE;
        }
    },
    EMPTY {
        @Override
        public Color nextTurn() {
            throw new UnsupportedOperationException("EMPTY는 nextTurn을 지원하지 않습니다");
        }
    };

    public static Color initialTurn() {
        return WHITE;
    }

    public abstract Color nextTurn();

    public boolean isTurnOf(final Piece piece) {
        return piece.isSideOf(this);
    }
}
