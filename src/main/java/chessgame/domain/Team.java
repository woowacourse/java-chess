package chessgame.domain;

import java.util.function.Function;

import chessgame.domain.point.Rank;

public enum Team {
    BLACK(String::toUpperCase, "검은", -1, Rank.SEVEN),
    WHITE(String::toLowerCase, "흰", 1, Rank.TWO);

    private final Function<String, String> name;
    private final String color;
    private final int direction;
    private final Rank startRank;

    Team(Function<String, String> name, String color, int direction, Rank startRank) {
        this.name = name;
        this.color = color;
        this.direction = direction;
        this.startRank = startRank;
    }

    public String calculate(String convertedName) {
        return name.apply(convertedName);
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
}
