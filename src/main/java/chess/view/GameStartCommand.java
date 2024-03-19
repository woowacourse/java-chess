package chess.view;

public enum GameStartCommand {

    START("start"),
    END("end"),
    ;

    private final String command;

    GameStartCommand(final String command) {
        this.command = command;
    }

    public static GameStartCommand map(final String rawInput) {
        if (START.command.equals(rawInput)) {
            return START;
        }
        if (END.command.equals(rawInput)) {
            return END;
        }
        throw new IllegalArgumentException(String.format("[ERROR] %s 또는 %s만 입력할 수 있습니다.", START, END));
    }

    @Override
    public String toString() {
        return this.command;
    }
}
