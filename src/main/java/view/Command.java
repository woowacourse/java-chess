package view;

public enum Command {
    START("start"),
    END("end");

    private String command;

    Command(String command) {
        this.command = command;
    }

    public static Command of(final String command) {
        if (command.equals(START.command)) {
            return START;
        }
        if (command.equals(END.command)) {
            return END;
        }
        throw new IllegalArgumentException("잘못된 명령어입니다.");
    }
}
