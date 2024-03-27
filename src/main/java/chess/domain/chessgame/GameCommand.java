package chess.domain.chessgame;

import java.util.Arrays;

public enum GameCommand {

    START("start"),
    MOVE("move"),
    END("end");

    private final String command;

    GameCommand(String command) {
        this.command = command;
    }

    public static GameCommand findGameCommand(String input) {
        return Arrays.stream(GameCommand.values())
                .filter(command -> command.isAvailableCommand(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력값과 일치하는 게임 명령이 없습니다."));
    }

    private boolean isAvailableCommand(String input) {
        return command.equals(input);
    }

    public static boolean isNotFinishedGame(GameCommand gameCommand) {
        return !gameCommand.equals(END);
    }

    public static boolean isGameStarted(GameCommand gameCommand) {
        return gameCommand.equals(START);
    }

    public static boolean isMovedChessPiece(GameCommand gameCommand) {
        return gameCommand.equals(MOVE);
    }
}
