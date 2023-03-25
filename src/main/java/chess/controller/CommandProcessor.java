package chess.controller;

import chess.domain.state.State;
import chess.domain.state.StateProcessor;
import chess.dto.CommandDto;
import chess.view.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public final class CommandProcessor {
    private final Map<Command, Function<CommandDto, State>> commands;

    private CommandProcessor(final Map<Command, Function<CommandDto, State>> commands) {
        this.commands = commands;
    }

    public static CommandProcessor create() {
        return new CommandProcessor(new HashMap<>());
    }

    public void register(Command command, Function<CommandDto, State> function) {
        commands.put(command, function);
    }

    public void execute(StateProcessor stateProcessor, CommandDto commandDto) {
        stateProcessor.changeState(commands.get(commandDto.getGameState())
                .apply(commandDto));
    }
}
