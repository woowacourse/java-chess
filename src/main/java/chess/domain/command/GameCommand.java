package chess.domain.command;

import java.util.Arrays;
import java.util.List;

public enum GameCommand {
    START("start", 0),
    END("end", 0),
    MOVE("move", 2);

    private final String command;
    private final int argsCount;

    GameCommand(String command, int argsCount) {
        this.command = command;
        this.argsCount = argsCount;
    }

    public static GameCommand from(CommandCondition commandCondition) {
        String command = commandCondition.command();
        List<String> args = commandCondition.args();

        return Arrays.stream(values())
                .filter(gameCommand -> gameCommand.isSameCommand(command) && gameCommand.isSameArgsCount(args))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게임 명령어입니다."));
    }

    private boolean isSameCommand(String command) {
        return this.command.equals(command);
    }

    private boolean isSameArgsCount(List<String> args) {
        return argsCount == args.size();
    }
}
