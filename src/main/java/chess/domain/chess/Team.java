package chess.domain.chess;

import java.util.function.Function;

public enum Team {
    BLACK((string) -> string),
    WHITE((string) -> string.toLowerCase());

    private Function<String, String> teamIdentifier;

    Team(Function<String, String> teamIdentifier) {
        this.teamIdentifier = teamIdentifier;
    }

    public Function<String, String> getFunction() {
        return teamIdentifier;
    }
}
