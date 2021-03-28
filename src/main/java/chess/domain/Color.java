package chess.domain;

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
    
    public boolean isWhite() {
        return this == WHITE;
    }
    
    public abstract Color next();
}
