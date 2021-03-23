package chess;

public final class Command {

    private static final int TARGET_INDEX = 2;
    private static final int SOURCE_INDEX = 1;
    private static final int COMMAND_INDEX = 0;
    private static final int TOTAL_MOVE_COMMAND_SIZE = 3;

    private final String command;
    private final String[] commands;

    public Command() {
        this("");
    }

    public Command(final String command) {
        this.command = command;
        commands = command.split(" ");
    }

    public boolean isStart() {
        return "start".equals(command);
    }

    public boolean isEnd() {
        return "end".equals(command);
    }

    public boolean isStatus() {
        return "status".equals(command);
    }

    public boolean isExit() {
        return "exit".equals(command);
    }

    public boolean isMove() {
        return "move".equals(commands[COMMAND_INDEX]);
    }

    public String sourcePosition() {
        validate();
        return commands[SOURCE_INDEX];
    }

    public String targetPosition() {
        validate();
        return commands[TARGET_INDEX];
    }

    private void validate() {
        if (!isMove()) {
            throw new IllegalArgumentException("이동 명령이 아닙니다.");
        }
        if (commands.length != TOTAL_MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("이동 명령의 인자수가 맞지 않습니다.");
        }
    }
}
