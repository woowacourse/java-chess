package chess.domain.piece;

import java.util.function.Function;

public enum Team {
    WHITE(Character::toLowerCase, "백"),
    BLACK(Character::toUpperCase, "흑");

    private final Function<Character, Character> teamRepresentation;
    private final String name;

    Team(Function<Character, Character> teamRepresentation, String name) {
        this.teamRepresentation = teamRepresentation;
        this.name = name;
    }

    public Function<Character, Character> getTeamRepresentation() {
        return teamRepresentation;
    }

    public String getName() {
        return name;
    }
}
