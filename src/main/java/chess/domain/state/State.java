package chess.domain.state;

import chess.dao.dto.StateType;
import chess.domain.ChessBoard;
import chess.domain.Result;

public interface State {

    State start();

    State end();

    State move(String source, String target);

    boolean isStarted();

    boolean isFinished();

    StateType getStateType();

    Result winner();

    ChessBoard chessBoard();
}
