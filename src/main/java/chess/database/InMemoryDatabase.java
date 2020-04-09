package chess.database;

import chess.web.ChessCommand;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase {
    private List<ChessCommand> commands;

    public InMemoryDatabase() {
        this.commands = new ArrayList<>();
    }

    public void add(ChessCommand command) {
        commands.add(command);
    }

    public void clear() {
        commands.clear();
    }

    public List<ChessCommand> get() {
        return commands;
    }
}
