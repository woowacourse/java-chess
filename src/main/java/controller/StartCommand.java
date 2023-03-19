package controller;

public enum StartCommand {
    START,
    END;

    public static StartCommand from(String command) {
        try {
            return StartCommand.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("start 또는 end만 입력해야 합니다.");
        }
    }
}
