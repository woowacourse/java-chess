package chess.controller;

import java.util.Arrays;
import java.util.List;

public enum GameCommand {
    MOVE("move", 3, "move 명령어는 '도착지'와 '출발지'에 대한 정보를 입력해야합니다."),
    END("end", 1, "end 명령어는 값을 하나만 입력해야합니다.");

    private final String gameCommand;
    private final int inputSize;
    private final String errorMessage;

    GameCommand(String gameCommand, int inputSize, String errorMessage) {
        this.gameCommand = gameCommand;
        this.inputSize = inputSize;
        this.errorMessage = errorMessage;
    }

    public static GameCommand of(String input) {
        return Arrays.stream(values())
                .filter(command -> command.gameCommand.equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 명령어를 입력했습니다."));
    }

    public static void validate(List<String> input) {
        GameCommand gameCommand = GameCommand.of(input.get(0));
        if (input.size() != gameCommand.inputSize) {
            throw new IllegalArgumentException(gameCommand.errorMessage);
        }
    }
}
