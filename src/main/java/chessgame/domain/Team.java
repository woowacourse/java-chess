package chessgame.domain;

import java.util.Arrays;
import java.util.function.Function;

import chessgame.domain.point.Rank;

public enum Team {
    BLACK(String::toUpperCase, "검은", -1, Rank.SEVEN, "black"),
    WHITE(String::toLowerCase, "흰", 1, Rank.TWO, "white");

    private final Function<String, String> printName;
    private final String color;
    private final int direction;
    private final Rank startRank;
    private final String name;

    Team(Function<String, String> printName, String color, int direction, Rank startRank, String name) {
        this.printName = printName;
        this.color = color;
        this.direction = direction;
        this.startRank = startRank;
        this.name = name;
    }

    public static Team find(String name) {
        return Arrays.stream(Team.values())
            .filter(team -> team.name.equals(name))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("팀 이름이 잘못됐습니다"));
    }

    public String convertName(String convertedName) {
        return printName.apply(convertedName);
    }

    public String color() {
        return color;
    }

    public int direction() {
        return direction;
    }

    public Rank startRank() {
        return startRank;
    }

    public String getName() {
        return name;
    }
}
