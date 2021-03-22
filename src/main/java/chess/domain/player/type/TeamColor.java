package chess.domain.player.type;

import java.util.Arrays;

public enum TeamColor {
    WHITE("white"),
    BLACK("black");

    private final String value;

    TeamColor(String value) {
        this.value = value;
    }

    public static TeamColor of(String teamColorInput) {
        return Arrays.stream(TeamColor.values())
            .filter(teamColor -> teamColor.value.equals(teamColorInput))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀 색깔 입니다."));
    }
}
