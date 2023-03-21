package controller;

public enum ProgressCommand {
    END,
    MOVE;

    public static ProgressCommand from(final String command) {
        try {
            return ProgressCommand.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("move source위치 target위치 또는 end만 입력해야 합니다.");
        }
    }
}
