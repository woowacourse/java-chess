package model.piece;

import model.direction.Destination;
import model.direction.Route;
import model.direction.WayPoints;
import model.piece.role.Role;
import model.piece.role.RoleStatus;
import model.piece.role.Square;
import model.position.Position;

public class Piece {
    private Role role;

    public Piece(final Role role) {
        this.role = role;
    }

    public Route findRoute(final Position source, final Position target) {
        return this.role.findRoute(source, target);
    }

    public void moveTo(final Destination destination) {
        Piece target = destination.target();
        target.role = role;
        role = new Square();
    }

    public void validateMoving(final WayPoints wayPoints, final Destination destination) {
        Piece target = destination.target();
        role.validateMoving(wayPoints, target.role);
    }

    public boolean isOccupied() {
        return role.isOccupied();
    }

    public RoleStatus roleStatus() {
        return role.status();
    }

    public Color color() {
        return role.color();
    }
}
