package chess.domain.state;

import chess.domain.Board;

public interface State {

    State start();

    State end();

    boolean isRunning();

    Board getBoard();

    State move();
}
