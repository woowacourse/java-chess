package chess.view;

public enum GameCommand {

    START("start"),
    END("end"),
    MOVE("move"),
    ;

    private final String command;

    GameCommand(final String command) {
        this.command = command;
    }

    public static GameCommand map(final String rawInput) {
        if (START.command.equals(rawInput)) {
            return START;
        }
        if (END.command.equals(rawInput)) {
            return END;
        }
        if (MOVE.command.equals(rawInput)) {
            return MOVE;
        }

        throw new IllegalArgumentException("[ERROR] start, end, move만 입력할 수 있습니다.");
    }

    @Override
    public String toString() {
        return this.command;
    }
}
