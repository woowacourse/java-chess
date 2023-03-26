package chess.controller;

import chess.domain.piece.Team;

import java.util.Arrays;

public enum TeamName {

    WHITE(Team.WHITE, "White"),
    BLACK(Team.BLACK, "Black");

    private final Team team;
    private final String name;

    TeamName(Team team, String name) {
        this.team = team;
        this.name = name;
    }

    public static String getNameByTeam(Team team) {
        return Arrays.stream(TeamName.values())
                .filter(teamName -> team == teamName.team)
                .map(TeamName::getName)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 팀입니다."));
    }

    private String getName() {
        return name;
    }
}
