package chess.controller;

import chess.model.board.Board;
import chess.model.state.State;

public interface Command {

    State executeTo(Board board);

    boolean isStart();
}
