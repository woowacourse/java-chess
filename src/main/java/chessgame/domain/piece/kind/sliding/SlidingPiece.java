package chessgame.domain.piece.kind.sliding;

import chessgame.domain.piece.Piece;
import chessgame.domain.piece.Pieces;
import chessgame.domain.piece.attribute.Color;
import chessgame.domain.piece.attribute.point.Point;
import chessgame.domain.piece.attribute.point.Movement;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class SlidingPiece extends Piece {
    protected SlidingPiece(final Point point, final Color color) {
        super(point, color);
    }

    protected final Set<Point> findLegalMovePoints(final Pieces pieces) {
        return getMovableDirection().stream()
                .flatMap(direction -> findLegalMovePointByDirection(pieces, direction).stream())
                .collect(Collectors.toSet());
    }

    protected abstract Set<Movement> getMovableDirection();

    private Set<Point> findLegalMovePointByDirection(final Pieces pieces, final Movement direction) {
        final var legalMovePoints = new HashSet<>(findEmptyPoints(pieces, direction));
        legalMovePoints.add(findExistPoint(pieces, direction));

        return removeSameTeam(pieces, legalMovePoints);
    }

    private Set<Point> findEmptyPoints(final Pieces pieces, final Movement direction) {
        return Stream.iterate(point, point -> point.canMove(direction), point -> point.move(direction))
                .filter(point -> point.canMove(direction))
                .map(point -> point.move(direction))
                .takeWhile(pieces::hasNothing)
                .collect(Collectors.toSet());
    }

    private Point findExistPoint(final Pieces pieces, final Movement direction) {
        return Stream.iterate(point, point -> point.canMove(direction), point -> point.move(direction))
                .filter(point -> !point.equals(this.point))
                .filter(pieces::hasPiece)
                .findFirst()
                .orElse(findEndPoint(direction));
    }

    private Point findEndPoint(final Movement direction) {
        var currentPosition = point;
        while (currentPosition.canMove(direction)) {
            currentPosition = currentPosition.move(direction);
        }
        return currentPosition;
    }

    private Set<Point> removeSameTeam(final Pieces pieces, final HashSet<Point> legalMovePoints) {
        return legalMovePoints.stream()
                .filter(point -> !pieces.isFriend(this, point))
                .collect(Collectors.toSet());
    }
}
