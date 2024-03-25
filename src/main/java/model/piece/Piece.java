package model.piece;

import model.direction.Direction;
import model.direction.Route;
import model.piece.state.Role;
import model.piece.state.Square;
import model.position.Position;

public class Piece {
    private Role role;

    public Piece(final Role role) {
        this.role = role;
    }

    public Route findRoute(final Position source, final Position target) {
        return this.role.findRoute(source, target);
    }

    public void moveTo(final Direction direction, final Piece target) {
        role.validateMoveTo(direction, target.role);
        target.role = role;
        role = new Square();
    }

    public boolean isOccupied() {
        return role.isOccupied();
    }

    public Role role() {
        return role;
    }
}
