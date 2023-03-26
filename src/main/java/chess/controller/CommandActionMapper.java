package chess.controller;

import chess.dto.CommandRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum CommandActionMapper {

    START("start", ChessGameController::start),
    MOVE("move", ChessGameController::move),
    END("end", ChessGameController::end),
    STATUS("status", ChessGameController::status),
    EXIT("exit", ChessGameController::forceQuit);

    private static final Map<String, CommandAction> actionByCommand = Arrays.stream(values())
            .collect(Collectors.toMap(value -> value.command, value -> value.commandAction));
    private final String command;
    private final CommandAction commandAction;

    CommandActionMapper(final String command, final CommandAction commandAction) {
        this.command = command;
        this.commandAction = commandAction;
    }

    public static AppStatus execute(ChessGameController chessGameController, CommandRequest commandRequest) {
        String command = commandRequest.getCommand();
        if (!actionByCommand.containsKey(command)) {
            throw new IllegalArgumentException("해당 요청으로 실행할 수 있는 기능이 없습니다.");
        }
        CommandAction action = actionByCommand.get(command);
        chessGameController.validateCommandRequest(commandRequest);
        return action.execute(chessGameController, commandRequest);
    }

    public String getCommand() {
        return command;
    }
}
