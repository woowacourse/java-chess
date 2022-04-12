package chess.domain;

import java.util.Arrays;

public enum Team {
    BLACK("black"),
    WHITE("white");

    private static final String UNVALID_TEAM_EXCEPTION = "[ERROR] 유효하지 않은 팀입니다.";
    private static final String UNVALID_TEAM_NAME_EXCEPTION = "[ERROR] 유효하지 않은 팀 이름입니다.";
    private final String name;

    Team(String name) {
        this.name = name;
    }

    public boolean isSame(Team other) {
        return this.equals(other);
    }

    public static String of(Team team) {
        return Arrays.stream(Team.values())
                .filter(it -> it.equals(team))
                .map(it -> it.name)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UNVALID_TEAM_EXCEPTION));
    }

    public static Team of(String name) {
        return Arrays.stream(Team.values())
                .filter(it -> it.name.equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(UNVALID_TEAM_NAME_EXCEPTION));
    }
}
