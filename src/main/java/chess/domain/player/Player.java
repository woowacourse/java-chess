package chess.domain.player;

import java.util.function.Function;

public enum Player {

    BLACK(String::toUpperCase),
    WHITE(String::toLowerCase);

    private Function<String, String> nameDecider;

    Player(Function<String, String> nameDecider) {
        this.nameDecider = nameDecider;
    }

    public String decideName(String name) {
        return nameDecider.apply(name);
    }
}
