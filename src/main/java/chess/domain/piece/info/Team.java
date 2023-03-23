package chess.domain.piece.info;

import chess.domain.position.Rank;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Team {
    BLACK,
    WHITE,
    EMPTY;

    public static List<Team> RealTeams = Arrays.stream(Team.values()).filter(Team::isRealTeam)
        .collect(
            Collectors.toList());

    public static Team initialOf(Rank rank) {
        if (rank == Rank.SEVEN || rank == Rank.EIGHT) {
            return BLACK;
        }
        if (rank == Rank.ONE || rank == Rank.TWO) {
            return WHITE;
        }
        return EMPTY;
    }

    private boolean isRealTeam() {
        return !this.equals(Team.EMPTY);
    }
    public boolean isEmpty() {
        return this.equals(Team.EMPTY);
    }
}
