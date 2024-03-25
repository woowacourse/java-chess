package model.piece.role;

import model.direction.Direction;
import model.direction.Route;
import model.piece.Color;
import model.position.Position;
import model.shift.Shift;

import java.util.Set;

public abstract class Role {

    private final Color color;
    private final Shift shift;

    protected Role(final Color color, final Shift shift) {
        this.color = color;
        this.shift = shift;
    }

    public boolean isOccupied() {
        return true;
    }

    public void validateMoveTo(final Direction direction, final Role target) {
        if (this.color == target.color) {
            throw new IllegalArgumentException("같은 진영의 기물이 목적 지점에 위치합니다.");
        }
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
}
