package chess.controller;

import chess.dto.CommandRequest;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum CommandActionMapper {

    START("start", ChessController::start),
    MOVE("move", ChessController::move),
    END("end", ChessController::end),
    EXIT("exit", ChessController::forceQuit);

    private static final Map<String, CommandAction> actionByCommand = Arrays.stream(values())
            .collect(Collectors.toMap(value -> value.command, value -> value.commandAction));
    private final String command;
    private final CommandAction commandAction;

    CommandActionMapper(final String command, final CommandAction commandAction) {
        this.command = command;
        this.commandAction = commandAction;
    }

    public static AppStatus execute(ChessController chessController, CommandRequest commandRequest) {
        CommandAction action = actionByCommand.getOrDefault(commandRequest.getCommand(),
                (controller, request) -> {
                    throw new IllegalArgumentException("해당 요청으로 실행할 수 있는 기능이 없습니다.");
                });
        return action.execute(chessController, commandRequest);
    }

    public String getCommand() {
        return command;
    }
}
