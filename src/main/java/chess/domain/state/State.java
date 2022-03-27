package chess.domain.state;

import chess.domain.Board;

public interface State {

    State start();

    State end();

    State changeTurn();

    Board board();
}
