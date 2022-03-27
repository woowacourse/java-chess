package chess.domain.command;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.state.State;

public class Start extends CommandState {

    private static final String DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;
    private static final int MOVE_COMMAND_SIZE = 3;

    public Start(State state) {
        this.state = state;
    }

    @Override
    public boolean isStart() {
        return true;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public CommandState execute(String input) {
        String[] commands = input.split(DELIMITER);
        Command command = Command.find(commands[COMMAND_INDEX]);

        if (command.equals(Command.MOVE) && commands.length == MOVE_COMMAND_SIZE) {
            return executeMove(commands);
        }
        if (command.equals(Command.END)) {
            return new End();
        }
        if (command.equals(Command.STATUE)) {
            return new Status(state);
        }
        throw new IllegalArgumentException(ILLEGAL_ARGUMENT_ERROR_MESSAGE);
    }

    private CommandState executeMove(String[] commands) {
        state = state.movePiece(Position.of(commands[SOURCE_INDEX]), Position.of(commands[DESTINATION_INDEX]));
        if (state.isFinished()) {
            return new End();
        }
        return new Start(state);
    }

    @Override
    public Board getBoard() {
        return state.getBoard();
    }

    @Override
    public StatusResult getStatus() {
        throw new UnsupportedOperationException();
    }
}
