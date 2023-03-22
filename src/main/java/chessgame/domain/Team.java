package chessgame.domain;

import java.util.function.Function;

public enum Team {
    BLACK("검은"),
    WHITE("흰");

    private final String color;

    Team(String color) {
        this.color = color;
    }


    public String color() {
        return color;
    }
}
