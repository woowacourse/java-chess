package controller;

public enum StartCommand {
    START,
    END;

    private static final String INVALID_GAME_OPTION = "start 또는 end만 입력해야 합니다.";

    public static StartCommand from(String command) {
        try {
            return StartCommand.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(INVALID_GAME_OPTION);
        }
    }
}
