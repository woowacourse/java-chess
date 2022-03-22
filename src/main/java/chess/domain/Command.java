package chess.domain;

public class Command {

    private static final String START = "start";
    private static final String END = "end";
    private static final String INVALID_COMMAND_EXCEPTION_MESSAGE = "유효한 명령이 아닙니다.";
    private final String command;

    public Command(String command) {
        validate(command);
        this.command = command;
    }

    private void validate(String command) {
        if (!(command.equals(START) || command.equals(END))) {
            throw new IllegalArgumentException(INVALID_COMMAND_EXCEPTION_MESSAGE);
        }
    }

    public boolean isStart() {
        return command.equals(START);
    }

    public boolean isEnd() {
        return command.equals(END);
    }
}
