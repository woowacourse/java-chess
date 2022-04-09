package chess.domain;

import chess.domain.piece.Team;

public enum Result {
    BLACK_WIN("BLACK"),
    DRAW("DRAW"),
    WHITE_WIN("WHITE");

    private final String teamName;

    Result(String teamName) {
        this.teamName = teamName;
    }

    public static Result of(Team winTeam) {
        if (winTeam.equals(Team.WHITE)) {
            return WHITE_WIN;
        }
        return BLACK_WIN;
    }

    public String getTeamName() {
        return teamName;
    }
}
