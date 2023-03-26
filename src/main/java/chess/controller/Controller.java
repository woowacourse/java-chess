package chess.controller;

import chess.domain.Board;
import chess.domain.Chess;
import chess.domain.Color;
import chess.domain.point.Point;
import chess.domain.point.Points;
import chess.domain.state.Start;
import chess.domain.state.State;
import chess.dto.Command;
import chess.view.GameState;
import chess.view.InputView;
import chess.view.OutputView;

import static chess.domain.Color.*;

public final class Controller {
    public void run() {
        OutputView.printStartMessage();
        State state = new Start(Chess.create(Board.create(), Points.create()));
        while (state.isNotEnd()) {
            state = nextState(state);
            OutputView.printChessBoard(state.getChess());
        }
    }

    private State nextState(State state) {
        try {
            return processChess(state);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            nextState(state);
        }
        return state;
    }

    private State processChess(State state) {
        final Command command = InputView.inputGameState();
        state = start(state, command);
        state = move(state, command);
        state = end(state, command);
        state = status(state, command);
        return state;
    }

    private State start(final State state, final Command command) {
        if (command.getGameState() == GameState.START) {
            return state.start();
        }
        return state;
    }

    private State move(final State state, final Command command) {
        if (command.getGameState() == GameState.MOVE) {
            return state.move(command);
        }
        return state;
    }

    private State end(final State state, final Command command) {
        if (command.getGameState() == GameState.END) {
            announceCurrentChessState(state);
            return state.end();
        }
        return state;
    }

    private State status(final State state, final Command command) {
        if (command.getGameState() == GameState.STATUS) {
            announceCurrentChessState(state);
            return state.status();
        }
        return state;
    }

    private void announceCurrentChessState(final State state) {
        final Point blackPoint = state.getPointBy(BLACK);
        final Point whitePoint = state.getPointBy(WHITE);
        final Color winner = state.getWinner();
        OutputView.printChessStatus(blackPoint, whitePoint, winner);
    }
}
