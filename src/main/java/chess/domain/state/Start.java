package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.result.BoardResult;
import chess.domain.state.command.CommandType;
import chess.domain.state.exception.UnsupportedCommandException;
import chess.domain.team.Team;

public class Start implements State {

    private final Board board;
    private final Team team;
    private CommandType commandType;

    public Start() {
        board = Board.createWithInitialLocation();
        team = Team.WHITE;
    }

    private static void validate(final String command) {
        CommandType commandType = CommandType.from(command);
        if (!commandType.equals(CommandType.START) && !commandType.equals(CommandType.END)) {
            throw new UnsupportedCommandException(commandType);
        }
    }

    @Override
    public void receive(final String command) {
        validate(command);
        commandType = CommandType.from(command);
    }

    @Override
    public State next() {
        if (commandType.equals(CommandType.START)) {
            return new Wait(board, team);
        }
        return new End();
    }

    @Override
    public State before() {
        return this;
    }

    @Override
    public BoardResult bringResult() {
        return new BoardResult(board.toList());
    }

    @Override
    public ResultType bringResultType() {
        return ResultType.BOARD;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean isMovable() {
        return false;
    }

    @Override
    public boolean needsParam() {
        return true;
    }
}
