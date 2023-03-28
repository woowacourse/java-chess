package chess.controller;

import chess.dto.CommandDto;
import chess.view.Command;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

public final class CommandController {
    private final Map<Command, Consumer<CommandDto>> commands;

    private CommandController(final Map<Command, Consumer<CommandDto>> commands) {
        this.commands = commands;
    }

    public static CommandController create() {
        return new CommandController(new EnumMap<>(Command.class));
    }

    public void register(Command command, Consumer<CommandDto> consumer) {
        commands.put(command, consumer);
    }

    public void execute(CommandDto commandDto) {
        commands.get(commandDto.getGameState()).accept(commandDto);
    }
}
