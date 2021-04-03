package chess.domain.player.type;

import java.util.Arrays;

public enum TeamColor {
    WHITE("white", "백"),
    BLACK("black", "흑");

    private final String value;
    private final String name;

    TeamColor(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public static TeamColor of(String color) {
        return Arrays.stream(values())
            .filter(teamColor -> teamColor.value.equals(color))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 팀 색깔 입니다."));
    }

    public TeamColor oppositeTeamColor() {
        if (this == WHITE) {
            return BLACK;
        }
        return WHITE;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public String oppositeTeamColorName() {
        return oppositeTeamColor().getName();
    }
}
