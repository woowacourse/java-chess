package chess.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Piece {
    private final Role role;
    private final Side side;

    private Piece(final Role role, final Side side) {
        this.role = role;
        this.side = side;
    }

    public static List<Piece> createInitialPieces(final Side side) {
        List<Piece> pieces = new ArrayList<>();
        Role[] roles = Role.values();
        Arrays.stream(roles).forEach(role -> {
            for (int i = 0; i < role.getCount(); i++) {
                pieces.add(new Piece(role, side));
            }
        });
        return pieces;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return role.name();
    }
}
