package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Result;

public interface State {

    State start();

    State end();

    State move(String source, String target);

    boolean isRunning();

    boolean isFinished();

    Result winner();

    ChessBoard chessBoard();

    StateType getStateType();
}
