package chess.domain.piece;

public enum Color {
    BLACK {
        @Override
        public Color next() {
            return WHITE;
        }
    }, WHITE {
        @Override
        public Color next() {
            return BLACK;
        }
    }, BLANK {
        @Override
        public Color next() {
            throw new IllegalStateException("잘못된 상태입니다.");
        }
    };
    
    public boolean isBlack() {
        return this == BLACK;
    }
    
    public abstract Color next();
}
