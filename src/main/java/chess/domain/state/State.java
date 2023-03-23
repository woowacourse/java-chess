package chess.domain.state;

import chess.domain.board.Board;
import chess.view.Command;

public interface State {
    boolean isEnd();
    State progress(Command command, Board board);
}
