package chess.controller;

import chess.controller.state.Ready;
import chess.controller.state.State;
import chess.domain.game.Command;
import chess.domain.game.Game;
import chess.domain.piece.Camp;
import chess.view.InputView;
import chess.view.OutputView;

public class ChessController {
    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";

    private Game game;
    private State state;

    public ChessController() {
        this.game = new Game();
        this.state = new Ready();
    }

    public void run() {
        OutputView.printGameStartMessage();

        while (state.isRunning()) {
            executeState();
        }
    }

    private void executeState() {
        try {
            final Command command = InputView.readCommand();
            executeCommand(command);
        } catch (final Exception e) {
            System.err.println(ERROR_MESSAGE_PREFIX + e.getMessage());
        }
    }

    private void executeCommand(final Command command) {
        if (command.isStart()) {
            state = state.start();
            game = new Game();
            OutputView.printChessBoard(game.getPieces());
        }
        if (command.isMove()) {
            state = state.next();
            playTurn(command);
        }
        if (command.isStatus()) {
            state = state.status();
            final double whiteScore = game.calculateScore(Camp.WHITE);
            final double blackScore = game.calculateScore(Camp.BLACK);
            final Camp winner = game.judgeWinner();
            OutputView.printStatus(whiteScore, blackScore, winner);
        }
        if (command.isEnd()) {
            state = state.end();
        }
    }

    private void playTurn(final Command command) {
        try {
            game.move(command.getSource(), command.getTarget());
            OutputView.printChessBoard(game.getPieces());
        } catch (final IllegalArgumentException e) {
            System.err.println(ERROR_MESSAGE_PREFIX + e.getMessage());
        }

        if (game.judgeWinner() != Camp.EMPTY) {
            state = state.kingDead();
        }
    }
}
