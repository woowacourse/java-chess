package chess.domain.piece;

import java.util.Arrays;

public enum Team {
    BLACK("흑"),
    WHITE("백"),
    NONE(".");

	private final String name;

    Team(String name) {
        this.name = name;
    }

    public Team next() {
        return Arrays.stream(values())
                .filter(team -> !team.equals(this))
                .findFirst()
                .orElseThrow(NullPointerException::new);
    }

    public static String getPieceName(Piece piece) {
        if (piece.isSameTeam(BLACK)) {
            return piece.getName();
        }
        return piece.getName().toLowerCase();
    }

    public String getName() {
        return name;
    }
}
