package controller;

public enum ProgressCommand {
    END,
    MOVE;

    private static final String INVALID_GAME_OPTION = "move 또는 end만 입력해야 합니다.";

    public static ProgressCommand from(String command) {
        try {
            return ProgressCommand.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(INVALID_GAME_OPTION);
        }
    }
}
