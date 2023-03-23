package status;

enum Command {
    START,
    END,
    MOVE;

    private static final String INVALID_GAME_OPTION = "start 또는 end만 입력해야 합니다.";

    public static Command from(final String command) {
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(INVALID_GAME_OPTION);
        }
    }
}
