package chess.domain.command;

import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.position.StatusResult;
import chess.domain.board.Piece;
import chess.domain.state.State;
import java.util.Map;

public abstract class Command {

    protected static final String START = "start";
    protected static final String END = "end";

    public static Command of(String input) {
        if (input.equals(START)) {
            return new Start(State.start(Board.getInitializedInstance()));
        }
        if (input.equals(END)) {
            return new End();
        }
        throw new IllegalArgumentException(START + " 혹은 " + END + "를 입력해주세요");
    }

    public abstract boolean isStart();

    public abstract boolean isStatus();

    public abstract Command execute(String command);

    public abstract Map<Position, Piece> getBoard();

    public abstract StatusResult getStatus();
}
