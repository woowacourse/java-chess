package domain.piece;

import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;
import domain.piece.kind.PieceStatus;

import dto.PieceDto;
import java.util.Objects;
import java.util.Set;

public abstract class Piece {

    protected Point point;
    protected final Color color;

    protected Piece(final Point point, final Color color) {
        this.point = point;
        this.color = color;
    }

    public abstract PieceStatus status();

    public Piece move(final Point destination, final Pieces pieces) {
        validateDifferentPoint(destination);
        final var movablePoints = findLegalMovePoints(pieces);
        validateMovablePoint(!movablePoints.contains(destination), "말을 움직일 수 없습니다.");
        return update(destination);
    }

    private void validateMovablePoint(boolean movablePoints, String s) {
        if (movablePoints) {
            throw new IllegalArgumentException(s);
        }
    }

    private void validateDifferentPoint(Point destination) {
        validateMovablePoint(point.equals(destination), "동일한 위치로 이동할 수 없습니다.");
    }

    protected abstract Set<Point> findLegalMovePoints(Pieces pieces);

    protected abstract Piece update(Point point);


    public boolean isEqualPoint(final Point point) {
        return this.point.equals(point);
    }

    public boolean isSameColor(final Piece piece) {
        return this.color == piece.color;
    }

    public boolean isOpposite(final Piece piece) {
        return this.color != piece.color;
    }

    public boolean isWhite() {
        return this.color == Color.WHITE;
    }

    public PieceDto toDto() {
        return new PieceDto(point.toDto(), this.status().value(), color);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final Piece piece)) return false;
        return Objects.equals(point, piece.point) && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(point, color);
    }

}
