package chess.domain.player;

import java.util.Arrays;

public enum Team {

    WHITE("WHITE"),
    BLACK("BLACK");

    private final String name;

    Team(String name) {
        this.name = name;
    }

    public static Team from(String team) {
        return Arrays.stream(Team.values())
                .filter(it -> it.name.equals(team))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀입니다."));
    }

    public String getName() {
        return name;
    }
}
