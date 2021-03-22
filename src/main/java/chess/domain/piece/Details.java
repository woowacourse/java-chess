package chess.domain.piece;

import chess.domain.Score;
import chess.domain.TeamColor;

public final class Details {

    private final String name;
    private final TeamColor teamColor;
    private final Score score;
    private final boolean iterable;

    public Details(String name, TeamColor teamColor, Score score, boolean iterable) {
        this.name = name;
        this.teamColor = teamColor;
        this.score = score;
        this.iterable = iterable;
    }

    public boolean isSameColor(TeamColor teamColor) {
        return teamColor.equals(this.teamColor);
    }

    public boolean iterable() {
        return iterable;
    }

    public TeamColor color() {
        return teamColor;
    }

    public Score score() {
        return score;
    }

    public String name() {
        return name;
    }
}
