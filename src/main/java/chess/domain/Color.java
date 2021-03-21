package chess.domain;

import chess.domain.piece.Direction;

import java.util.List;

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
    }, BLANK("우승자가 존재하지 않습니다.") {
        @Override
        public Color next() {
            throw new IllegalStateException("Turn이 BLANK로 설정되어 있습니다. 게임을 다시 시작해주세요.");
        }
    };
    
    private final String winner;
    
    Color(String winner) {
        this.winner = winner;
    }
    
    public String color() {
        return this.winner;
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
    
    public List<Direction> getPawnDirections() {
        if (this == BLACK) {
            return Direction.blackPawnDirection();
        }
        
        return Direction.whitePawnDirection();
    }
}
