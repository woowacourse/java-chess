package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Rook implements Piece {
    private static final List<Rook> blackRooks = new ArrayList<>();
    private static final List<Rook> whiteRooks = new ArrayList<>();

    static {
        addRooks(blackRooks, Team.BLACK);
        addRooks(whiteRooks, Team.WHITE);
    }

    private final Team team;

    private Rook(final Team team) {
        this.team = team;
    }

    private static void addRooks(final List<Rook> rooks, final Team team) {
        for (int i = 0; i < 2; i++) {
            rooks.add(new Rook(team));
        }
    }

    public static List<Rook> of(final Team team) {
        if (team == Team.BLACK) {
            return List.copyOf(blackRooks);
        }
        return List.copyOf(whiteRooks);
    }

    @Override
    public String getTeam() {
        return team.toString();
    }
}
