package chess.domain;

import java.util.ArrayList;
import java.util.List;

public final class Pawn implements Piece {
    private static final List<Pawn> blackPawns = new ArrayList<>();
    private static final List<Pawn> whitePawns = new ArrayList<>();

    static {
        addPawns(blackPawns, Team.BLACK);
        addPawns(whitePawns, Team.WHITE);
    }

    private final Team team;

    private Pawn(Team team) {
        this.team = team;
    }

    private static void addPawns(final List<Pawn> pawns, final Team team) {
        for (int i = 0; i < 8; i++) {
            pawns.add(new Pawn(team));
        }
    }

    public static List<Pawn> of(final Team team) {
        if (team == Team.BLACK) {
            return List.copyOf(blackPawns);
        }
        return List.copyOf(whitePawns);
    }
}
