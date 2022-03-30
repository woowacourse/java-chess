package chess.domain.command;

import chess.domain.board.Board;
import chess.domain.state.Ready;
import chess.domain.state.State;

public abstract class CommandState {

    protected static final String ILLEGAL_ARGUMENT_ERROR_MESSAGE = "명령어가 잘못되었습니다";

    protected State state;

    public static CommandState of(String input) {
        Command command = Command.find(input);

        if (command == Command.START) {
            return new Start(Ready.start(Board.getBasicInstance()));
        }
        if (command == Command.END) {
            return new End();
        }
        throw new IllegalArgumentException(ILLEGAL_ARGUMENT_ERROR_MESSAGE);
    }

    public abstract boolean isStart();

    public abstract boolean isStatus();

    public abstract CommandState execute(String command);

    public abstract Board getBoard();

    public abstract StatusResult getStatus();
}
