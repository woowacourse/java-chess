package chess.domain;

import java.util.Arrays;

public enum GameStatus {
    WAIT("wait"),
    START("start"),
    MOVE("move"),
    CATCH("catch"),
    END("end"),
    NONE("none");

    private final String label;

    GameStatus(String label) {
        this.label = label;
    }

    public static GameStatus findByLabel(String label) {
        return Arrays.stream(values())
                .filter(value -> value.label.equals(label))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException());
    }

    public String getLabel() {
        return label;
    }
}
