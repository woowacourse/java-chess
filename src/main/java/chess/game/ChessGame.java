package chess.game;

import chess.ChessBoard;
import chess.command.Command;
import chess.piece.Color;
import chess.state.State;

public class ChessGame {

    private State state;

    public ChessGame(State state) {
        this.state = state;
    }

    public void start() {
        state = state.start();
    }

    public void execute(Command command) {
        if (command.isEnd()) {
            state = state.finished();
        }
        if (command.isMove()) {
            state = state.move(command.from(), command.to());
        }
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public boolean isGameEnd() {
        return state.isGameEnd();
    }

    public Color winner() {
        return state.winner();
    }

    public Score score() {
        return state.score();
    }

    public ChessBoard chessBoard() {
        return state.chessBoard();
    }
}
