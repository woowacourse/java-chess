package model.piece;

import model.piece.state.Role;

public class PieceHolder {
    private Role role;

    public PieceHolder(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public Color color() {
        return role.getColor();
    }
}
