package chess.domain.chess;

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
            throw new UnsupportedOperationException("해당 컬러는 BLANK입니다.");
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
