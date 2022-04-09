package chess.domain;

import java.util.Arrays;

public enum Team {

    WHITE("white"),
    BLACK("black"),
    NEUTRALITY("neutrality");

    private final String name;

    Team(String name) {
        this.name = name;
    }

    public static Team find(String name) {
        return Arrays.stream(values())
                .filter(team -> team.name.equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못된 팀 이름입니다."));
    }

    public boolean isBlack() {
        return this == Team.BLACK;
    }

    public String getName() {
        return name;
    }

    public boolean isNeutrality(Team neutrality) {
        return this == NEUTRALITY;
    }

    public boolean isWhite() {
        return this == WHITE;
    }
}
