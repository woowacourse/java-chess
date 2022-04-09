package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Command;

public interface State {

    static State getState(String turn) {
        if (turn.equals("white")) {
            return new White();
        }
        if (turn.equals("black")) {
            return new Black();
        }
        if (turn.equals("ready")) {
            return new Ready();
        }
        return new End();
    }

    boolean isEnd();

    State execute(Command command, ChessBoard chessBoard);

    String getTurn();
}
