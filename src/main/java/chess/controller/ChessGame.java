package chess.controller;

import chess.domain.Side;
import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.gamestate.State;
import chess.exception.InvalidCommandException;
import chess.view.OutputView;

public class ChessGame {
    private State state;

    public ChessGame(State state) {
        this.state = state;
    }

    public void start() {
        state = state.start();
    }

    public void execute(Command command) {
        if (command.isMove()) {
            state = state.move(command.source(), command.target());
        }
        if (command.isStatus()) {
            state = state.status();
            OutputView.print(state.score());
        }
        if (command.isEnd()) {
            state = state.finished();
        }

        throw new InvalidCommandException();
    }

    public State state() {
        return state;
    }

    public Board board() {
        return state.board();
    }

    public Side currentTurn() {
        return state.currentTurn();
    }

    public Side winner() {
        return state.winner();
    }
}
