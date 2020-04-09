package chess.database;

import chess.web.ChessCommand;

import java.util.List;

public class InMemoryChessDao implements ChessDao {

    private final InMemoryDatabase commands = new InMemoryDatabase();

    @Override
    public void addCommand(ChessCommand command) {
        commands.add(command);
    }

    @Override
    public void clearCommands() {
        commands.clear();
    }

    @Override
    public List<ChessCommand> selectCommands() {
        return commands.get();
    }
}
