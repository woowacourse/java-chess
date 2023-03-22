package chess.view;

import java.util.Arrays;

public enum GameCommandView {

    START("start"),
    MOVE("move"),
    STATUS("status"),
    END("end");

    private final String command;

    GameCommandView(String command) {
        this.command = command;
    }

    public static boolean isValidCommandWithoutMove(String command) {
        return Arrays.stream(GameCommandView.values())
                .filter(gameCommandView -> gameCommandView != MOVE)
                .anyMatch(gameCommandView -> gameCommandView.command.equals(command));
    }

    public static boolean isValidMoveCommand(String command) {
        return MOVE.command.equals(command);
    }

    public static boolean isStartCommand(String command) {
        return START.command.equals(command);
    }

    public static boolean isStatusCommand(String command) {
        return STATUS.command.equals(command);
    }

    public static boolean isEndCommand(String command) {
        return END.command.equals(command);
    }
}
