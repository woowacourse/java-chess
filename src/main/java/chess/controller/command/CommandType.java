package chess.controller.command;

import chess.domain.Position;

import java.util.Arrays;

public enum CommandType {
    START("start"),
    END("end"),
    MOVE("move"),
    STATUS("status");

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_POSITION_INDEX = 1;
    private static final int TARGET_POSITION_INDEX = 2;
    private static final int MOVE_COMMAND_INPUT_LENGTH = 3;
    private final String name;

    CommandType(String name) {
        this.name = name;
    }

    public static CommandType of(String GameCommand) {
        return Arrays.stream(values())
                .filter(command -> command.name.equals(GameCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 명령어입니다."));
    }

    public static Command makeCommand(String[] commandInput) {
        CommandType commandType = CommandType.of(commandInput[COMMAND_INDEX]);
        if (commandType.equals(START)) {
            return new StartCommand();
        }
        if (commandType.equals(END)) {
            return new EndCommand();
        }
        if (commandType.equals(MOVE)) {
            return createMovecommand(commandInput);
        }
        return new StatusCommand();
    }

    private static Command createMovecommand(String[] commandInput) {
        if (commandInput.length != MOVE_COMMAND_INPUT_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 'move sourcePosition targetPosition'을 모두 입력하세요.");
        }

        return new MoveCommand(Position.findPosition(commandInput[SOURCE_POSITION_INDEX]), Position.findPosition(commandInput[TARGET_POSITION_INDEX]));
    }
}
