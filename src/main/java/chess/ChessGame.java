package chess;

import chess.state.Ready;
import chess.state.State;

public class ChessGame {

    private State state;

    public ChessGame() {
        this.state = new Ready();
    }

    public void start() {
        state = state.start();
    }

    public Chessboard getChessBoard() {
        return state.getChessboard();
    }
}
