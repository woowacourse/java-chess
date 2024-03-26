package domain.piece.kind.sliding;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;
import domain.piece.attribute.point.TempDirection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class SlidingPiece extends Piece {
    protected SlidingPiece(Point point, Color color) {
        super(point, color);
    }

    protected final Set<Point> findLegalMovePoints(Pieces pieces) {
        return getMovableDirection().stream()
                .flatMap(direction -> findLegalMovePointByDirection(pieces, direction).stream())
                .collect(Collectors.toSet());
    }

    protected abstract Set<TempDirection> getMovableDirection();

    private Set<Point> findLegalMovePointByDirection(Pieces pieces, TempDirection direction) {
        final var legalMovePoints = new HashSet<>(findEmptyPoints(pieces, direction));
        legalMovePoints.add(findExistPoint(pieces, direction));

        return removeSameTeam(pieces, legalMovePoints);
    }

    private Set<Point> findEmptyPoints(Pieces pieces, TempDirection direction) {
        return Stream.iterate(point, point -> point.canMove(direction), point -> point.move(direction))
                .filter(point -> point.canMove(direction))
                .map(point -> point.move(direction))
                .takeWhile(pieces::hasNothing)
                .collect(Collectors.toSet());
    }

    private Point findExistPoint(Pieces pieces, TempDirection direction) {
        return Stream.iterate(point, point -> point.canMove(direction), point -> point.move(direction))
                .filter(point -> !point.equals(this.point))
                .filter(pieces::hasPiece)
                .findFirst()
                .orElse(findEndPoint(direction));
    }

    private Point findEndPoint(TempDirection direction) {
        var currentPosition = point;
        while (currentPosition.canMove(direction)) {
            currentPosition = currentPosition.move(direction);
        }
        return currentPosition;
    }

    private Set<Point> removeSameTeam(Pieces pieces, HashSet<Point> legalMovePoints) {
        return legalMovePoints.stream()
                .filter(point -> !pieces.isFriend(this, point))
                .collect(Collectors.toSet());
    }
}
