package chess.piece;

import chess.position.File;
import chess.position.Position;
import java.math.BigDecimal;
import java.util.Objects;

public abstract class Piece {

    private final Color color;
    private final Position position;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public Piece transfer(Position to) {
        if (getPosition().equals(to)) {
            throw new IllegalArgumentException("동일한 위치로 기물을 움직일 수 없습니다.");
        }

        if (!isPossibleMovement(to)) {
            throw new IllegalArgumentException(String.format(
                    "%s의 기물을 %s에서 %s로 이동할 수 없습니다.", getClass().getSimpleName(), position, to));
        }

        return createNewPiece(to);
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }

    public boolean isSameFile(File file) {
        return position.isSameFile(file);
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKing() {
        return false;
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
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, getClass());
    }

    @Override
    public String toString() {
        return "Piece{" +
                "color=" + color +
                ", type=" + getClass().getSimpleName();
    }

    public abstract BigDecimal getPoint();

    protected abstract Piece createNewPiece(Position to);

    protected abstract boolean isPossibleMovement(Position to);
}
