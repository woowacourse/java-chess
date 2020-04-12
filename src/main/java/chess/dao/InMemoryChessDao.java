package chess.dao;

import chess.database.InMemoryDatabase;
import chess.dto.CommandDto;

import java.util.List;

public class InMemoryChessDao implements ChessDao {

    private final InMemoryDatabase commands = new InMemoryDatabase();

    @Override
    public void addCommand(CommandDto command) {
        commands.add(command);
    }

    @Override
    public void clearCommands() {
        commands.clear();
    }

    @Override
    public List<CommandDto> selectCommands() {
        return commands.get();
    }
}
