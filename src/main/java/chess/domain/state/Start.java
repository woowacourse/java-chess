package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.state.command.CommandType;
import chess.domain.state.exception.UnsupportedCommandException;
import chess.domain.team.Team;
import chess.utils.BoardUtil;
import java.util.List;

public class Start implements State<List<Piece>> {

    private final Board board;
    private final Team team;
    private CommandType commandType;

    public Start() {
        board = BoardUtil.generateInitialBoard();
        team = Team.WHITE;
    }

    private static void validate(String command) {
        CommandType commandType = CommandType.from(command);
        if (!commandType.equals(CommandType.START) && !commandType.equals(CommandType.END)) {
            throw new UnsupportedCommandException(commandType);
        }
    }

    @Override
    public void receive(String command) {
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
    public List<Piece> result() {
        return board.toList();
    }

    @Override
    public ResultType resultType() {
        return ResultType.BOARD;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean needsParam() {
        return true;
    }
}
