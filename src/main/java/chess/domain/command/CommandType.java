package chess.domain.command;

import java.util.Arrays;
import java.util.function.Predicate;

public enum CommandType {
    START("start", inputCommand -> inputCommand.equals("start")),
    END("end", inputCommand -> inputCommand.equals("end")),
    MOVE("move", inputCommand -> inputCommand.equals("move")),
    STATUS("status", inputCommand -> inputCommand.equals("status"));

    private String command;
    private Predicate<String> isSameCommand;

    CommandType(String command, Predicate<String> isSameCommand) {
        this.command = command;
        this.isSameCommand = isSameCommand;
    }

    public static CommandType findValueOf(Command inputCommand) {
        return Arrays.stream(CommandType.values())
                .filter(commandType -> commandType.isSameCommandWith(inputCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령입니다."));
    }

    private boolean isSameCommandWith(Command inputCommand) {
        return isSameCommand.test(inputCommand.getCommand());
    }

    public boolean checkInitialCommand() {
        return (isStart() || isEnd());
    }

    public boolean isEnd() {
        return equals(END);
    }

    public boolean isStart() {
        return equals(START);
    }
}
