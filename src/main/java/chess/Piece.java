package chess;

import static chess.PieceType.*;
import static chess.PieceType.ROOK;

import java.util.Objects;

public class Piece {

    private final Color color;
    private final PieceType type;
    private Position position;
    private boolean isFirstMove;

    public Piece(Color color, PieceType type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
        this.isFirstMove = true;
    }

    public void move(Position to) {
        if (type == ROOK) {
            if (!position.isCollinear(to)) {
                throw new IllegalArgumentException();
            }
        } else {
            if (isInvalidMovement(to)) {
                throw new IllegalArgumentException();
            }
            this.isFirstMove = false;
        }
        this.position = to;
    }

    private boolean isInvalidMovement(Position to) {
        return isInvalidDirection(to) || isInvalidDistance(to) ||
            isMoveOverOneSpaceAfterFirstMove(to);
    }

    private boolean isInvalidDirection(Position to) {
        Direction direction = position.getDirection(to);
        return !position.isSameRow(to) || color.isBackward(direction);
    }

    private boolean isInvalidDistance(Position to) {
        return position.getDistance(to) > 2;
    }

    private boolean isMoveOverOneSpaceAfterFirstMove(Position to) {
        return !isFirstMove && position.getDistance(to) >= 2;
    }

    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }

    public Color getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color && type == piece.type && Objects
            .equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, type, position);
    }

    @Override
    public String toString() {
        return "Piece{" +
            "color=" + color +
            ", type=" + type +
            ", position=" + position +
            '}';
    }

    public static Piece pawn(Color color, Position position) {
        return new Piece(color, PAWN, position);
    }

    public static Piece king(Color color, Position position) {
        return new Piece(color, KING, position);
    }

    public static Piece queen(Color color, Position position) {
        return new Piece(color, QUEEN ,position);
    }

    public static Piece bishop(Color color, Position position) {
        return new Piece(color, BISHOP, position);
    }

    public static Piece knight(Color color, Position position) {
        return new Piece(color, KNIGHT, position);
    }

    public static Piece rook(Color color, Position position) {
        return new Piece(color, ROOK, position);
    }
}
