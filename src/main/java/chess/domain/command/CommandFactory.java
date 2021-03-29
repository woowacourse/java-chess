package chess.domain.command;

public class CommandFactory {
    private CommandFactory() {
    }

    public static Command initialCommand(final String command) {
        if ("start".equals(command)) {
            return new Start();
        }
        if ("end".equals(command)) {
            return new End();
        }
        throw new UnsupportedOperationException("start 또는 end를 입력해주세요!");
    }
}
