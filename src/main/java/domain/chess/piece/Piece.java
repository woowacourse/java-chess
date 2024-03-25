package domain.chess.piece;

import domain.chess.Color;
import domain.chess.Direction;
import domain.chess.Point;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public abstract class Piece implements Movable {

    protected Point point;
    protected final Color color;

    protected Piece(final Point point, final Color color) {
        this.point = point;
        this.color = color;
    }

    public abstract PieceStatus getStatus();

    public void move(final Point point) {
        this.point = point;
    }

    public boolean canMove(final Point movePoint) {
        return canMove(movePoint, List.of());
    }

    protected boolean canMovePointOne(final Point movePoint) {
        final Direction direction = this.point.calculate(movePoint);
        if (direction.canMovePoint(this.point)) {
            return direction.movePoint(this.point)
                            .equals(movePoint);
        }
        return false;
    }

    protected final boolean notExistPieceInPath(final Point endPoint, final Pieces pieces) {
        return !hasAnyPieceInPath(endPoint, pieces);
    }

    protected final boolean hasAnyPieceInPath(final Point endPoint, final Pieces pieces) {
        final Direction direction = this.point.calculate(endPoint);
        final Point pathPoint = direction.movePoint(this.point);

        final Stream<Point> pathPoints = Stream.iterate(
                pathPoint,
                movePoint -> direction.canMovePoint(movePoint) && movePoint.notEquals(endPoint),
                direction::movePoint);

        return pathPoints
                .anyMatch(pieces::containPieceWithPoint);
    }

    protected final boolean notExistPieces(final Pieces pieces, final Point... points) {
        return Arrays.stream(points)
                     .allMatch(value -> notExistPiece(value, pieces));
    }

    protected final boolean notExistPiece(final Point findPoint, final Pieces pieces) {
        return !hasAnyPiece(findPoint, pieces);
    }

    protected final boolean hasAnyPiece(final Point findPoint, final Pieces pieces) {
        return pieces.containPieceWithPoint(findPoint);
    }

    protected final boolean hasFriendPiece(final Point endPoint, final Pieces pieces) {
        final Optional<Piece> optionalPiece = pieces.findPieceWithPoint(endPoint);
        if (optionalPiece.isEmpty()) {
            return false;
        }
        final Piece toPiece = optionalPiece.get();
        return this.color == toPiece.color;
    }

    protected final boolean hasEnemyPiece(final Point endPoint, final Pieces pieces) {
        final Optional<Piece> optionalPiece = pieces.findPieceWithPoint(endPoint);
        if (optionalPiece.isEmpty()) {
            return false;
        }
        final Piece toPiece = optionalPiece.get();
        return this.color != toPiece.color;
    }

    protected final boolean hasEnemyPieceOrEmpty(final Point endPoint, final Pieces pieces) {
        return !hasFriendPiece(endPoint, pieces);
    }

    public boolean isEqualPoint(final Point point) {
        return this.point.equals(point);
    }

    public boolean isBlack() {
        return this.color.isBlack();
    }

    public boolean isWhite() {
        return this.color.isWhite();
    }

    public Point getPoint() {
        return this.point;
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
