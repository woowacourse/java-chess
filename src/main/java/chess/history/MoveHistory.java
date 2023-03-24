package chess.history;

import chess.command.Command;
import chess.command.MoveCommand;
import database.MoveDAO;
import java.util.List;

public final class MoveHistory implements History {
    
    private final MoveDAO moveDAO = new MoveDAO("moves");
    
    private final List<Command> commands;
    
    public MoveHistory() {
        this.commands = this.moveDAO.fetchCommands();
    }
    
    @Override
    public void add(final MoveCommand command) {
        this.commands.add(command);
        this.moveDAO.addMove(command);
    }
    
    @Override
    public void reset() {
        this.commands.clear();
        this.moveDAO.resetMoves();
    }
    
    @Override
    public List<Command> getCommands() {
        return this.commands;
    }
}
