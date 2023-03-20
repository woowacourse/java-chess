package chess.domain.piece;

import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    
    public static final String PIECE_CANNOT_MOVE_ERROR_MESSAGE = "이(가) 움직일 수 없는 위치입니다.";
    public static final String OUT_OF_DISTANCE_ERROR_MESSAGE = "이(가) 움직일 수 없는 거리입니다.";
    
    private final Color color;
    private final PieceType type;
    private final List<Direction> movableDirections;
    
    public Piece(final Color color, final PieceType type, final List<Direction> directions) {
        this.color = color;
        this.type = type;
        this.movableDirections = directions;
    }
    
    public abstract void canMove(Position start, Position end);
    
    protected void checkDirection(Direction direction) {
        if (!movableDirections.contains(direction)) {
            throw new IllegalArgumentException(type.name() + PIECE_CANNOT_MOVE_ERROR_MESSAGE);
        }
    }
    
    protected void checkDistance(final Position start, final Position end, final List<Integer> movableDistances) {
        int distance = start.calculateDistance(end);
        if (!movableDistances.contains(distance)) {
            throw new IllegalArgumentException(type.name() + OUT_OF_DISTANCE_ERROR_MESSAGE);
        }
    }
    
    public boolean isSameColor(Color color) {
        return this.color == color;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.color);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Piece piece = (Piece) o;
        return this.color == piece.color;
    }
    
    public PieceType getType() {
        return type;
    }
    
    public boolean isWhite() {
        return color == Color.WHITE;
    }
    
    public boolean isEmpty() {
        return type == PieceType.EMPTY;
    }
    
    
}
