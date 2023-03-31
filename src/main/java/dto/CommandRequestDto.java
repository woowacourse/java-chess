package dto;

import java.util.List;
import java.util.Map;

import controller.Command;

public class CommandRequestDto {
    private static final int COMMAND_INDEX = 0;
    private static final int CURRENT_SQUARE_INDEX = 1;
    private static final int TARGET_SQUARE_INDEX = 2;

    private static final Map<String, Integer> COMMANDS_AND_REQUEST_SIZE =
        Map.of(Command.START.getCommand(), 1,
            Command.END.getCommand(), 1,
            Command.MOVE.getCommand(), 3,
            Command.STATUS.getCommand(), 1);

    private final List<String> commandRequest;

    public CommandRequestDto(List<String> commandRequest) {
        validateCommandRequest(commandRequest);
        this.commandRequest = commandRequest;
    }

    private void validateCommandRequest(List<String> commandRequest) {
        String command = commandRequest.get(COMMAND_INDEX);
        validateCommand(command);
        validateCommandForm(commandRequest);
    }

    private void validateCommand(String command) {
        if (COMMANDS_AND_REQUEST_SIZE.containsKey(command)) {
            return;
        }
        throw new IllegalArgumentException("잘못된 명령입니다.");
    }

    private void validateCommandForm(List<String> commandRequest) {
        if (commandRequest.size() != COMMANDS_AND_REQUEST_SIZE.get(commandRequest.get(COMMAND_INDEX))) {
            throw new IllegalArgumentException("잘못된 명령입니다.");
        }
    }

    public Command getCommand() {
        return Command.find(commandRequest.get(COMMAND_INDEX));
    }

    public String getCurrentSquareName() {
        return commandRequest.get(CURRENT_SQUARE_INDEX);
    }

    public String getTargetSquareName() {
        return commandRequest.get(TARGET_SQUARE_INDEX);
    }
}
