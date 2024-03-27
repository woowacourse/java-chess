package model.piece.role;

import model.direction.Route;
import model.direction.WayPoints;
import model.piece.Color;
import model.piece.Piece;
import model.position.Position;
import model.shift.Shift;

import java.util.Set;

public sealed abstract class Role permits Bishop, King, Knight, Pawn, Queen, Rook, Square {

    private final Color color;
    private final Shift shift;

    protected Role(final Color color, final Shift shift) {
        this.color = color;
        this.shift = shift;
    }

    public abstract RoleStatus status();

    public void validateMoving(final WayPoints wayPoints, final Role target) {
        validateNotSameColor(target);
        validateNotExistWayPoints(wayPoints);
    }

    private void validateNotSameColor(final Role target) {
        if (color == target.color) {
            throw new IllegalArgumentException("같은 진영의 기물이 목적 지점에 위치합니다.");
        }
    }

    private void validateNotExistWayPoints(final WayPoints wayPoints) {
        wayPoints.points()
                 .values()
                 .stream()
                 .filter(Piece::isOccupied)
                 .findAny()
                 .ifPresent(position -> {
                     throw new IllegalArgumentException("목적 지점까지의 경로에 기물이 위치하여 이동할 수 없습니다.");
                 });
    }

    public Route findRoute(final Position source, final Position destination) {
        return findPossibleAllRoute(source).stream()
                                           .filter(route -> route.contains(destination))
                                           .findAny()
                                           .orElseThrow(() -> new IllegalArgumentException("해당 기물이 이동할 수 없는 좌표입니다"));
    }

    public Set<Route> findPossibleAllRoute(final Position position) {
        return shift.routes(position);
    }

    public boolean isOccupied() {
        return true;
    }

    public Color color() {
        return color;
    }
}
