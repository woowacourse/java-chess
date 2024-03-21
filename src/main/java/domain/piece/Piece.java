package domain.piece;

import domain.chessboard.Square;
import domain.position.Position;
import java.util.Objects;

public class Piece {
    private final PieceType pieceType;
    private Position position;

    public Piece(final PieceType pieceType, final Position position) {
        this.pieceType = pieceType;
        this.position = position;
    }

    // TODO: setter 해결하기
    public void move(final Position target) {
        this.position = target;
    }

    public boolean isEqualPosition(final Position target) {
        return target.equals(position);
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
        return Objects.equals(pieceType, piece.pieceType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType);
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public boolean isEqualColor(final Piece target) {
        return pieceType.isEqualColor(target.getColor());
    }

    public boolean isEqualColor(final Color color) {
        return pieceType.isEqualColor(color);
    }

    private Color getColor() {
        return pieceType.getColor();
    }

    public boolean canMove(final Square source, final Square target) {
        return pieceType.canMove(source.getPosition(), target.getPosition());
    }

    public boolean isPawn() {
        return pieceType.isPawn();
    }

    public boolean isNotKnight() {
        return pieceType.isNotKnight();
    }
}
