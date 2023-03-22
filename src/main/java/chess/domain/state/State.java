package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.square.Square;

public interface State {
    State start();

    void move(final Square source, final Square target);

    State end();

    Board getBoard();

    boolean isRunning();
}
