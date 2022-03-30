package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Command;

public final class Ready implements State {

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public State execute(Command command, ChessBoard chessBoard) {
        if (command.isStart()) {
            return new White();
        }
        if (command.isEnd()) {
            return new End();
        }
        return new Ready();
    }
}
