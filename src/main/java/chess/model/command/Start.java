package chess.model.command;

import chess.model.board.Board;
import chess.model.state.Running;
import chess.model.state.State;

public class Start implements Command {

    @Override
    public State executeTo(Board board) {
        return new Running(board);
    }

    @Override
    public boolean isStart() {
        return true;
    }
}
