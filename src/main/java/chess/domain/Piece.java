package chess.domain;

public class Piece {
    private final Role role;
    private final Side side;

    public Piece(final Role role, final Side side) {
        this.role = role;
        this.side = side;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return role.name();
    }
}
