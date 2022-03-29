package chess.model.command;

import chess.model.board.Board;
import chess.model.state.State;

public interface Command {

    State executeTo(final Board board);

    boolean isStart();
}
