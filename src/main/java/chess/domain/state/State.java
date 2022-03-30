package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Command;

public interface State {
    State start();

    State stop();

    State changeTurn(Command command, ChessBoard chessBoard);

    boolean isEnd();
}
