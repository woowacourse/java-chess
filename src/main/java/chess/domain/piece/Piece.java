package chess.domain.piece;

import chess.domain.position.Position;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    
    private final Color color;
    private final PieceType type;
    
    public Piece(final Color color, final PieceType type) {
        this.color = color;
        this.type = type;
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
    
    public PieceType getType() {
        return type;
    }
}
