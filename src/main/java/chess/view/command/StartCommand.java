package chess.view.command;

public class StartCommand {

    private static final String START_COMMAND = "start";
    private static final String END_COMMAND = "end";

    private final boolean isStart;

    private StartCommand(boolean isStart) {
        this.isStart = isStart;
    }

    public static StartCommand from(String input) {
        if (START_COMMAND.equals(input)) {
            return new StartCommand(true);
        }

        if (END_COMMAND.equals(input)) {
            return new StartCommand(false);
        }

        String message = String.format("존재하지 않는 명령어입니다. %s와 %s 중 하나를 입력해주세요.", START_COMMAND, END_COMMAND);
        throw new IllegalArgumentException(message);
    }

    public boolean isStart() {
        return isStart;
    }
}
