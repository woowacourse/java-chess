package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.location.Location;
import chess.domain.result.Result;
import chess.domain.state.command.CommandType;
import chess.domain.state.exception.UnsupportedCommandException;
import chess.domain.team.Team;

public class Wait implements State {

    private static final String BLANK = " ";
    private static final int SOURCE_LOCATION_INDEX = 1;
    private static final int TARGET_LOCATION_INDEX = 2;

    private final Board board;
    private final Team team;
    private CommandType commandType;
    private String command;

    public Wait(final Board board, final Team team) {
        this.board = board;
        this.team = team;
    }

    private static void validate(final String command) {
        CommandType commandType = CommandType.from(command);
        if (commandType.equals(CommandType.START)) {
            throw new UnsupportedCommandException(commandType);
        }
    }

    @Override
    public void receive(final String command) {
        validate(command);
        this.commandType = CommandType.from(command);
        this.command = command;
    }

    @Override
    public State next() {
        if (commandType.equals(CommandType.STATUS)) {
            return new Status(board, team);
        }
        if (commandType.equals(CommandType.MOVE)) {
            return new Move(board, team, command);
        }
        return new End();
    }

    @Override
    public State before() {
        return this;
    }

    @Override
    public Result bringResult() {
        throw new UnsupportedCommandException("입력 대기중에는 결과를 요청할 수 없습니다.");
    }

    @Override
    public ResultType bringResultType() {
        return ResultType.NONE;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean needsParam() {
        return true;
    }

    @Override
    public boolean isMovable() {
        String[] words = command.split(BLANK);
        Location source = Location.convert(words[SOURCE_LOCATION_INDEX].trim());
        Location target = Location.convert(words[TARGET_LOCATION_INDEX].trim());

        try {
            board.validateMove(source, target, team);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
