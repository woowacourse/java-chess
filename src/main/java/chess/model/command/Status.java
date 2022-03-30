package chess.model.command;

import chess.model.board.Board;
import chess.model.state.Sleep;
import chess.model.state.State;

public class Status implements Command {

    @Override
    public State executeTo(final Board board) {
        return new Sleep(board);
    }

    @Override
    public boolean isStart() {
        return false;
    }
}
