package chess.domain;

import chess.domain.piece.Direction;

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
            throw new IllegalStateException("Turn이 BLANK로 설정되어 있습니다. 게임을 다시 시작해주세요.");
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
    
    public boolean isBlank() {
        return this == BLANK;
    }
    
    public abstract Color next();
    
    public Direction getPawnForwardDirection() {
        if (this == BLACK) {
            return Direction.SOUTH;
        }
        return Direction.NORTH;
    }
}
