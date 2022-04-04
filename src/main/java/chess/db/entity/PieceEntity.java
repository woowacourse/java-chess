package chess.db.entity;

import chess.domain.board.piece.Color;
import chess.domain.board.piece.PieceType;
import chess.domain.board.position.Position;
import java.util.Objects;

public class PieceEntity {

    private final Position position;
    private final PieceType type;
    private final Color color;

    public PieceEntity(Position position, PieceType type, Color color) {
        this.position = position;
        this.type = type;
        this.color = color;
    }

    public PieceEntity(String positionKey, String typeValue, String colorValue) {
        this.position = Position.of(positionKey);
        this.type = PieceType.valueOf(typeValue);
        this.color = Color.valueOf(colorValue);
    }

    public Position getPosition() {
        return position;
    }

    public String getPositionKey() {
        return position.toKey();
    }

    public PieceType getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PieceEntity that = (PieceEntity) o;
        return Objects.equals(position, that.position)
                && type == that.type
                && color == that.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, type, color);
    }

    @Override
    public String toString() {
        return "PieceEntity{" +
                "position=" + position +
                ", type=" + type +
                ", color=" + color +
                '}';
    }
}
