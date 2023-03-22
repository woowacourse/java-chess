package chess.domain.piece;

public enum Color {
    BLACK {
        @Override
        public Color change() {
            return WHITE;
        }
    }, WHITE {
        @Override
        public Color change() {
            return BLACK;
        }
    };

    public abstract Color change();
}
