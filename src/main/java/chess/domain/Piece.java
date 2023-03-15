package chess.domain;

import java.util.List;

public abstract class Piece {
    
    private final Color color;
    
    public Piece(Color color) {
        this.color = color;
    }
    
    List<Position> calculateMovablePositions(Position position) {
        return List.of();
    }
    
    boolean isWhite() {
        return this.color == Color.WHITE;
    }
}
