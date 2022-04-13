package chess.domain.player;

import java.util.stream.Stream;

public enum Team {

    WHITE("화이트"),
    BLACK("블랙");

    private final String name;

    Team(String name) {
        this.name = name;
    }

    public static Team from(final String teamName) {
        return Stream.of(Team.values())
                .filter(team -> team.name.equals(teamName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 팀이 존재하지 않습니다."));
    }

    public String getName() {
        return name;
    }

    public Team next() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }
}
