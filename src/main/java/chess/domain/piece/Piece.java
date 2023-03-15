package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Position;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    
    private final Color color;
    
    public Piece(Color color) {
        this.color = color;
    }
    
    List<Position> calculateMovablePositions(Position position) {
        return List.of();
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return color == piece.color;
    }
    
    public boolean isWhite() {
        return this.color == Color.WHITE;
    }
}
