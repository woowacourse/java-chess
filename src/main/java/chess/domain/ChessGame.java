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

    public void start() {
        state = state.start();
    }

    public void stop() {
        state = state.stop();
    }

    public void move(Command command) {
        state.changeTurn(command, chessBoard);
    }
}
