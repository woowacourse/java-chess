package domain.piece.kind;

import domain.piece.Piece;
import domain.piece.Pieces;
import domain.piece.attribute.Color;
import domain.piece.attribute.point.Point;

import domain.piece.attribute.point.TempDirection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static domain.piece.attribute.point.TempDirection.LEFT_DOWN;
import static domain.piece.attribute.point.TempDirection.LEFT_UP;
import static domain.piece.attribute.point.TempDirection.RIGHT_DOWN;
import static domain.piece.attribute.point.TempDirection.RIGHT_UP;


public class Bishop extends Piece {
    private static final Set<TempDirection> directionList = Set.of(LEFT_UP, RIGHT_UP, LEFT_DOWN, RIGHT_DOWN);

    public Bishop(final Point point, final Color color) {
        super(point, color);
    }

    @Override
    protected Set<Point> findLegalMovePoints(Pieces pieces) {
        return directionList.stream()
                .flatMap(direction -> findLegalMovePointByDirection(pieces, direction).stream())
                .collect(Collectors.toSet());
    }

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

    @Override
    protected Piece update(Point point) {
        return null;
    }

    @Override
    public PieceStatus getStatus() {
        return PieceStatus.BISHOP;
    }

    public boolean canMove(final Point point) {
        return false;
    }
}
