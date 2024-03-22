package chess.view;

public enum GameCommand {
    START("start"),
    MOVE("move"),
    END("end"),
    ;

    private final String value;

    GameCommand(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
