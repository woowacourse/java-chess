package chess.dto;

import java.util.List;

public class CommandsDto {

    private final List<String> commands;

    public CommandsDto(final List<String> commands) {
        this.commands = commands;
    }

    public List<String> getCommands() {
        return commands;
    }
}
