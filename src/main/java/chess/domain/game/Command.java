package chess.domain.game;

import chess.domain.move.Position;

import java.util.Arrays;

public enum Command {
    START("start"),
    STATUS("status"),
    MOVE("move"),
    END("end");

    private final String command;

    Command(String command) {
        this.command = command;
    }

    public static boolean isExistCommand(String inputCommand) {
        return Arrays.stream(values())
                .anyMatch((o) -> (o.command.equals(inputCommand.split(" ")[0])));
    }

    public static boolean isEnd(String command) {
        return command.equals(END.getCommand());
    }

    public static boolean isMove(String command) {
        return command.contains(MOVE.getCommand());
    }

    public static boolean isStatus(String command) {
        return command.equals(STATUS.getCommand());
    }

    public static Position makeStartPosition(String command) {
        String startString = command.split(" ")[1];
        validateInputPosition(startString);

        return Position.of(startString);
    }

    public static Position makeTargetPosition(String command) {
        String targetString = command.split(" ")[2];
        validateInputPosition(targetString);

        return Position.of(targetString);
    }

    private static void validateInputPosition(String inputPosition) {
        if (inputPosition.length() != 2) {
            throw new IllegalArgumentException("이동 위치를 정확히 입력해주세요.");
        }
    }

    private String getCommand() {
        return command;
    }

    public boolean equals(String command) {
        return this.command.equals(command);
    }
}
