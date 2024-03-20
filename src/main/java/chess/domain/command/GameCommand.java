package chess.domain.command;

import java.util.Arrays;
import java.util.List;

public enum GameCommand {
    START("start", 1),
    END("end", 1),
    MOVE("move", 3);

    private static final int COMMAND_INDEX = 0;

    private final String command;
    private final int argsCount;

    GameCommand(String command, int argsCount) {
        this.command = command;
        this.argsCount = argsCount;
    }

    public static GameCommand from(List<String> inputCommand) {
        return Arrays.stream(values())
                .filter(gameCommand -> isSameCommand(inputCommand, gameCommand) && isSameArgsCount(inputCommand,
                        gameCommand))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게임 명령어입니다."));
    }

    private static boolean isSameCommand(List<String> inputCommand, GameCommand gameCommand) {
        return gameCommand.command.equals(inputCommand.get(COMMAND_INDEX));
    }

    private static boolean isSameArgsCount(List<String> inputCommand, GameCommand gameCommand) {
        return inputCommand.size() == gameCommand.argsCount;
    }
}
