package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.GameCommand;

public interface State {

    State proceed(final ChessBoard chessBoard, final GameCommand gameCommand);

    static State of(String value) {
        if (value.equals("blackrunning")) {
            return new BlackRunning();
        }
        if (value.equals("whiterunning")) {
            return new WhiteRunning();
        }
        if (value.equals("ready")) {
            return new Ready();
        }
        throw new IllegalArgumentException();
    }

    boolean isFinished();
}
