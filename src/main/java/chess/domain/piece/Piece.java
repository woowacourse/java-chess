package chess.domain.piece;

import chess.domain.position.Column;
import chess.domain.position.Position;
import java.util.Collection;
import java.util.Objects;

public abstract class Piece {

    private final Color color;
    private final Position position;

    protected Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public Piece transfer(Position to, Collection<Piece> pieces) {
        if (position.equals(to)) {
            throw new IllegalArgumentException("동일한 위치로 기물을 움직일 수 없습니다.");
        }
        if (!isPossibleMovement(to, pieces)) {
            throw new IllegalArgumentException(String.format(
                "%s의 기물을 %s에서 %s로 이동할 수 없습니다.", getClass().getSimpleName(), position, to));
        }
        if (hasObstacleBetweenPositions(to, pieces) || hasSameColorTargetPiece(to, pieces)) {
            throw new IllegalArgumentException(String.format(
                "%s의 기물을 %s에서 %s로 이동할 수 없습니다.", getClass().getSimpleName(), position, to));
        }
        return createNewPiece(to);
    }

    private boolean hasObstacleBetweenPositions(Position to, Collection<Piece> pieces) {
        if (!position.hasLinearPath(to)) {
            return false;
        }

        return this.position.getLinearPath(to).stream()
            .anyMatch(position -> hasPieceByPosition(position, pieces));
    }

    private boolean hasSameColorTargetPiece(Position to, Collection<Piece> pieces) {
        if (!hasPieceByPosition(to, pieces)) {
            return false;
        }

        Piece targetPiece = findPieceByPosition(to, pieces);
        return isSameColor(targetPiece.getColor());
    }

    private Piece findPieceByPosition(Position position, Collection<Piece> pieces) {
        return pieces.stream()
            .filter(piece -> piece.isSamePosition(position))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다."));
    }

    private boolean hasPieceByPosition(Position position, Collection<Piece> pieces) {
        return pieces.stream()
            .anyMatch(piece -> piece.isSamePosition(position));
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isSamePosition(Position position) {
        return this.position.equals(position);
    }

    public boolean isSameFile(Column column) {
        return position.isSameFile(column);
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

    protected abstract Piece createNewPiece(Position to);

    protected abstract boolean isPossibleMovement(Position to, Collection<Piece> pieces);

    public abstract boolean isPawn();

    public abstract boolean isKing();
}
