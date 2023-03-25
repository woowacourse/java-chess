package chess.controller;

import chess.dto.CommandDto;
import chess.view.Command;

import java.util.HashMap;
import java.util.Map;

public class ResultProcessor {
    private final Map<Command, Runnable> commands;

    private ResultProcessor(final Map<Command, Runnable> commands) {
        this.commands = commands;
    }

    public static ResultProcessor create() {
        return new ResultProcessor(new HashMap<>());
    }

    public void register(Command command, Runnable runnable) {
        commands.put(command, runnable);
    }

    public void execute(CommandDto commandDto) {
        commands.get(commandDto.getGameState())
                .run();
    }
}
