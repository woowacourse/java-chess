package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    private static final List<Knight> blackKnights = new ArrayList<>();
    private static final List<Knight> whiteKnights = new ArrayList<>();

    static {
        addKnights(blackKnights, Team.BLACK);
        addKnights(whiteKnights, Team.WHITE);
    }



    private Knight(final Team team) {
        super(team);
    }

    private static void addKnights(final List<Knight> Knights, final Team team) {
        for (int i = 0; i < 2; i++) {
            Knights.add(new Knight(team));
        }
    }

    public static List<Knight> of(final Team team) {
        if (team == Team.BLACK) {
            return List.copyOf(blackKnights);
        }
        return List.copyOf(whiteKnights);
    }

    @Override
    boolean isMovable(final Square from, final Square to, final Piece piece) {
        return isDifferentTeam(piece) && from.inLShape(to);
    }
}
