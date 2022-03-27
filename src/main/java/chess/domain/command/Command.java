package chess.domain.command;

import chess.domain.board.Board;
import chess.domain.state.Ready;

public abstract class Command {

    protected static final String START = "start";
    protected static final String END = "end";

    public static Command of(String input) {
        if (input.equals(START)) {
            return new Start(Ready.start(Board.getBasicInstance()));
        }
        if (input.equals(END)) {
            return new End();
        }
        throw new IllegalArgumentException(START + " 혹은 " + END + "를 입력해주세요");
    }

    public abstract boolean isStart();

    public abstract boolean isStatus();

    public abstract Command execute(String command);

    public abstract Board getBoard();

    public abstract StatusResult getStatus();
}
