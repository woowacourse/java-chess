package chess.domain.state;

import chess.Command;
import chess.domain.Status;
import chess.domain.board.Board;

public interface ChessState {

    ChessState execute(Command command, String... commandArgs);

    boolean isEnd();

    Board getBoard();

    Status createStatus();
}
