package chess.domain.command;

public class CommandFactory {
    private static final String COMMAND_ERROR = "[ERROR] 올바른 명령이 아닙니다.";

    public static Command selectFirstCommand(String input) {
        if ("start".equals(input)) {
            return new Start();
        }
        if ("end".equals(input)) {
            return new End();
        }
        throw new UnsupportedOperationException(COMMAND_ERROR);
    }
}
