package chessgame.domain;

import java.util.function.Function;

public enum Team {
    BLACK(String::toUpperCase, "검은"),
    WHITE(String::toLowerCase, "흰");

    private final Function<String, String> name;
    private final String color;

    Team(Function<String, String> name, String color) {
        this.name = name;
        this.color = color;
    }

    public String convertTeamName(String convertedName) {
        return name.apply(convertedName);
    }

    public String color() {
        return color;
    }
}
