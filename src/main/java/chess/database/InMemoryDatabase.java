package chess.database;

import chess.dto.CommandDto;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase {
    private List<CommandDto> commands;

    public InMemoryDatabase() {
        this.commands = new ArrayList<>();
    }

    public void add(CommandDto command) {
        commands.add(command);
    }

    public void clear() {
        commands.clear();
    }

    public List<CommandDto> get() {
        return commands;
    }
}
