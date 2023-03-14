package chess.domain;

public class Piece {
    private final Role role;

    public Piece(final Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return role.name();
    }
}
