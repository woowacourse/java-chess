package domain;

import java.util.List;

import service.Command;

public class CommandRequest {
    private static final int COMMAND_INDEX = 0;
    private static final int CURRENT_SQUARE_INDEX = 1;
    private static final int TARGET_SQUARE_INDEX = 2;
    private static final int SQUARE_FORM_SIZE = 2;
    private static final int START_AND_END_REQUEST_SIZE = 1;
    private static final int MOVE_COMMAND_REQUEST_SIZE = 3;

    private final List<String> commandRequest;

    public CommandRequest(List<String> commandRequest) {
        validateCommandRequest(commandRequest);
        this.commandRequest = commandRequest;
    }

    private void validateCommandRequest(List<String> commandRequest) {
        String command = commandRequest.get(COMMAND_INDEX);
        validateIfStartAndEnd(commandRequest, command);
        validateIfMove(commandRequest, command);
    }

    private void validateIfStartAndEnd(List<String> commandRequest, String command) {
        if (Command.isStart(command) || Command.isEnd(command)) {
            validateStartEndCommand(commandRequest);
        }
    }

    private void validateStartEndCommand(List<String> commandRequest) {
        if (commandRequest.size() != START_AND_END_REQUEST_SIZE) {
            throw new IllegalArgumentException("start, end 명령이 잘못되었습니다.");
        }
    }

    private void validateIfMove(List<String> commandRequest, String command) {
        if (Command.isMove(command)) {
            validateMoveCommand(commandRequest);
        }
    }

    private void validateMoveCommand(List<String> commandRequest) {
        if (commandRequest.size() != MOVE_COMMAND_REQUEST_SIZE
            || commandRequest.get(CURRENT_SQUARE_INDEX).length() != SQUARE_FORM_SIZE
            || commandRequest.get(TARGET_SQUARE_INDEX).length() != SQUARE_FORM_SIZE) {
            throw new IllegalArgumentException("move 명령이 잘못되었습니다.");
        }
    }

    public String getCommand() {
        return commandRequest.get(COMMAND_INDEX);
    }

    public String getCurrentSquareName() {
        return commandRequest.get(CURRENT_SQUARE_INDEX);
    }

    public String getTargetSquareName() {
        return commandRequest.get(TARGET_SQUARE_INDEX);
    }
}
