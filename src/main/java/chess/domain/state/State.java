package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Command;

public interface State {

    boolean isEnd();

    State execute(Command command, ChessBoard chessBoard);
}
