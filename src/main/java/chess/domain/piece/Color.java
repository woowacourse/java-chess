package chess.domain.piece;

public enum Color {
    BLACK("BLACK") {
        @Override
        public Color next() {
            return WHITE;
        }
    }, WHITE("WHITE") {
        @Override
        public Color next() {
            return BLACK;
        }
    }, BLANK("BLANK") {
        @Override
        public Color next() {
            throw new IllegalStateException("잘못된 상태입니다.");
        }
    };
    
    private final String color;
    
    Color(String color) {
        this.color = color;
    }
    
    public String color() {
        return this.color;
    }
    
    public boolean isBlack() {
        return this == BLACK;
    }
    
    public abstract Color next();
}
