package model.piece;

import model.piece.state.Role;
import model.piece.state.Square;
import model.position.Position;
import model.position.Route;

public class PieceHolder {
    private Role role;

    public PieceHolder(Role role) {
        this.role = role;
    }

    public Route findRoute(Position source, Position destination) {
        return this.role.findDirectRoute(source, destination);
    }

    public boolean isOccupied() {
        return role.isOccupied();
    }

    public void changeRoleTo(PieceHolder source) {
        checkSameColor(source.color());
        this.role = source.role;
    }

    public void leave() {
        this.role = new Square();
    }

    public void checkSameColor(Color color) {
        this.role.checkSameColor(color);
    }

    public Role getRole() {
        return role;
    }

    public Color color() {
        return role.getColor();
    }
}
