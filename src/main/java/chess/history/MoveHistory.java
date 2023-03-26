package chess.history;

import chess.command.Command;
import chess.command.CommandType;
import chess.command.MoveCommand;
import database.MoveDAO;
import java.util.List;

public final class MoveHistory implements History {
    
    public static final String INVALID_COMMAND_ADDED_ERROR_MESSAGE =
            HISTORY_ERROR_PREFIX + "이동 기록에 이동 명령어가 아닌 명령어가 추가되었습니다.";
    private final MoveDAO moveDAO = new MoveDAO("moves");
    
    private final List<Command> commands;
    
    public MoveHistory() {
        this.commands = this.moveDAO.fetchCommands();
    }
    
    private void validateCommand(final Command command) {
        if (command.getType() != CommandType.MOVE) {
            throw new IllegalArgumentException(INVALID_COMMAND_ADDED_ERROR_MESSAGE);
        }
    }
    
    @Override
    public void add(final Command command) {
        this.commands.add(command);
        this.validateCommand(command);
        this.moveDAO.addMove((MoveCommand) command);
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
