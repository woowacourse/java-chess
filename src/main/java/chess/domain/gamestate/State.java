package chess.domain.gamestate;

import chess.domain.board.Board;

public interface State {

    State start();

    Board getBoard();
}
