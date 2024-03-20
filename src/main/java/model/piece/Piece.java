package model.piece;

import model.piece.state.Role;

public class Piece {
    private Color color;
    private Role role;

    public Piece(Color color, Role role) {
        this.color = color;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public Color getColor(){
        return color;
    }
}
