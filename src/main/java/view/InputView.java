package view;

import domain.GameCommand;
import domain.position.Position;
import dto.CommandDto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class InputView {
    private static final String START_COMMAND = "start";
    private static final String MOVE_COMMAND = "move";
    private static final String STATUS_COMMAND = "status";
    private static final String SAVE_COMMAND = "save";
    private static final String END_COMMAND = "end";
    private static final Map<String, GameCommand> gameCommands = Map.of(
            START_COMMAND, GameCommand.START,
            MOVE_COMMAND, GameCommand.MOVE,
            STATUS_COMMAND, GameCommand.STATUS,
            SAVE_COMMAND, GameCommand.SAVE,
            END_COMMAND, GameCommand.END
    );
    private static final String COMMAND_DELIMITER = " ";
    private static final int COMMAND_TYPE_POSITION = 0;
    private static final int MOVE_COMMAND_SOURCE_POSITION = 1;
    private static final int MOVE_COMMAND_DESTINATION_POSITION = 2;

    private final Scanner sc = new Scanner(System.in);

    public CommandDto inputGameCommand() {
        List<String> input = Arrays.stream(sc.nextLine().split(COMMAND_DELIMITER)).toList();
        String commandType = input.get(COMMAND_TYPE_POSITION);

        if (!gameCommands.containsKey(commandType)) {
            throw new IllegalArgumentException("유효하지 않은 명령입니다.");
        }
        GameCommand command = gameCommands.get(commandType);

        if (input.size() == 3) {
            return includePositionToCommand(input, command);
        }
        return CommandDto.of(command);
    }

    private CommandDto includePositionToCommand(List<String> input, GameCommand command) {
        String sourcePosition = input.get(MOVE_COMMAND_SOURCE_POSITION);
        String destinationPosition = input.get(MOVE_COMMAND_DESTINATION_POSITION);
        Position source = PositionConvertor.convertPosition(sourcePosition);
        Position destination = PositionConvertor.convertPosition(destinationPosition);

        return CommandDto.of(command, source, destination);
    }
}
