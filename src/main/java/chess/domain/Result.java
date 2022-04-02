package chess.domain;

import chess.domain.piece.Team;

public enum Result {
    BLACK_WIN("검은팀"),
    DRAW("무승부"),
    WHITE_WIN("흰팀");

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
