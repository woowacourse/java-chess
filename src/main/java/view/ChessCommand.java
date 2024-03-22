package view;

public enum ChessCommand {

    START("start"),
    END("end"),
    MOVE("move");

    private final String commandText;

    ChessCommand(final String commandText) {
        this.commandText = commandText;
    }

    public static ChessCommand from(final String commandText) {
        for (final ChessCommand value : values()) {
            if (value.commandText.equals(commandText)) {
                return value;
            }
        }
        throw new IllegalArgumentException(String.format("%s는 없는 명령입니다.", commandText));
    }
}
