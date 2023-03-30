package chess.domain.pieces.component;

import java.util.Arrays;

public enum Team {
    BLACK("블랙"),
    WHITE("화이트"),
    NEUTRALITY("중립");

    private final String value;

    Team(String value) {
        this.value = value;
    }

    public static Team of(final String input) {

        return Arrays.stream(Team.values())
                .filter(team -> team.value.equals(input))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("[ERROR] 존재하지 않는 팀입니다."));
    }

    public String getValue() {
        return value;
    }
}
