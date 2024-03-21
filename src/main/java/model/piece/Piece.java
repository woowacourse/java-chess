package model.piece;

import model.piece.state.Role;

public class Piece {
    private Role role;

    public Piece(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public Color color() {
        return role.getColor();
    }
}
