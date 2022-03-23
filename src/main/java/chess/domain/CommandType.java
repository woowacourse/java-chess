package chess.domain;

public enum CommandType {
    START,
    END,
    MOVE;

    public static CommandType of(String command) {
        for (CommandType commandType : values()) {
            if (command.equalsIgnoreCase(commandType.name())) {
                return commandType;
            }
        }
        throw new IllegalArgumentException("유효한 커맨드가 아닙니다.");
    }

    public static boolean isSingleCommand(CommandType command) {
        return command == START || command == END;
    }
}
