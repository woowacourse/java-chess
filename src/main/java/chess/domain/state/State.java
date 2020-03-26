package chess.domain.state;

import chess.domain.MoveParameter;
import chess.domain.Turn;
import chess.domain.board.Board;

public interface State {

    State start();

    State move(MoveParameter moveParameter, Turn turn);

    State end();

    Board getBoard();
}
