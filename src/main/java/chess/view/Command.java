package chess.view;

import chess.domain.game.GameStatus;

import static chess.view.ErrorMessage.NO_COMMAND_ERROR_GUIDE_MESSAGE;

public enum Command {

    START("start", GameStatus.PLAYING),
    MOVE("move", GameStatus.PLAYING),
    END("end", GameStatus.GAME_OVER),
    STATUS("status", GameStatus.PLAYING);

    private final String value;
    private GameStatus gameStatus;

    Command(String value, GameStatus gameStatus) {
        this.value = value;
        this.gameStatus = gameStatus;
    }

    public static Command findCommandByName(String input) {
        for (Command command : Command.values()) {
            if (input.startsWith(command.value)) {
                return command;
            }
        }
        throw new IllegalArgumentException(NO_COMMAND_ERROR_GUIDE_MESSAGE.getErrorMessage());
    }

    public static boolean isStart(Command command) {
        return command.equals(Command.START);
    }

    public static boolean isStatus(Command command) {
        return command.equals(Command.STATUS);
    }

    public static boolean isEnd(Command command) {
        return command.equals(Command.END);
    }

    public static boolean isMove(Command command) {
        return command.equals(Command.MOVE);
    }

    public GameStatus getStatus() {
        return gameStatus;
    }
}
