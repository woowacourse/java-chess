package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Command;

public final class Ready implements State {
    @Override
    public State start() {
        return new White();
    }

    @Override
    public State stop() {
        return new End();
    }

    @Override
    public State changeTurn(Command command, ChessBoard chessBoard) {
        return new Ready();
    }
}
