package chess.domain;

import chess.domain.state.Ready;
import chess.domain.state.State;

public class ChessGame {

    private final ChessBoard chessBoard;
    private State state;

    public ChessGame() {
        state = new Ready();
        chessBoard = new ChessBoard();
    }

    private void start() {
        state = state.start();
    }

    private void stop() {
        state = state.stop();
    }

    public void progress(Command command) {
        if (command.isStart()) {
            start();
            return;
        }

        if (command.isEnd()) {
            stop();
            return;
        }

        state = state.changeTurn(command, chessBoard);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }
}
