package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Bishop implements Piece {

    private static final List<Bishop> blackBishops = new ArrayList<>();
    private static final List<Bishop> whiteBishops = new ArrayList<>();

    static {
        addBishops(blackBishops, Team.BLACK);
        addBishops(whiteBishops, Team.WHITE);
    }

    private final Team team;

    private Bishop(final Team team) {
        this.team = team;
    }

    private static void addBishops(final List<Bishop> bishops, final Team team) {
        for (int i = 0; i < 2; i++) {
            bishops.add(new Bishop(team));
        }
    }

    public static List<Bishop> of(final Team team) {
        if (team == Team.BLACK) {
            return List.copyOf(blackBishops);
        }
        return List.copyOf(whiteBishops);
    }
}
