package view;

import java.util.Arrays;
import java.util.List;

public enum GameCommand {
    START("start", 1),
    MOVE("move", 3),
    END("end", 1),
    STATUS("status", 1),
    LOAD("load", 2),
    NEW("new", 1),
    SAVE("save", 1);

    private final String gameCommand;
    private final int commandLine;

    GameCommand(String gameCommand, int commandLine) {
        this.gameCommand = gameCommand;
        this.commandLine = commandLine;
    }

    public static GameCommand from(List<String> gameCommand) {
        if (gameCommand == null) {
            throw new IllegalArgumentException("아무 값도 입력되지 않았습니다.");
        }

        return Arrays.stream(GameCommand.values())
                .filter(command -> command.gameCommand.equals(gameCommand.get(0)))
                .filter(command -> command.commandLine == gameCommand.size())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게임 커맨드입니다."));
    }
}
